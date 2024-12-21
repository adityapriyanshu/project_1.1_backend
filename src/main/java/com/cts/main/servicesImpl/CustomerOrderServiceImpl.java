//
//
//
//package com.cts.main.servicesImpl;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.cts.main.dtos.CustomerOrderDTO;
//import com.cts.main.entities.CustomerOrder;
//import com.cts.main.entities.MenuItem;
//import com.cts.main.repository.CustomerOrderRepository;
//import com.cts.main.repository.MenuItemRepository;
//import com.cts.main.services.CustomerOrderService;
//
//@Service
//public class CustomerOrderServiceImpl implements CustomerOrderService {
//
//    @Autowired
//    private CustomerOrderRepository customerOrderRepository;
//
//    @Autowired
//    private MenuItemRepository menuItemRepository;
//
//    @Override
//    public List<CustomerOrder> getAllOrders() {
//        return customerOrderRepository.findAll();
//    }
//
//    @Override
//    public CustomerOrder getOrderById(Long id) {
//        return customerOrderRepository.findById(id)
//            .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
//    }
//
//    @Override
//    public CustomerOrder createOrder(CustomerOrderDTO orderDTO) {
//        List<MenuItem> menuItems = menuItemRepository.findAllById(orderDTO.getOrderItems());
//
//        double totalPrice = menuItems.stream().mapToDouble(MenuItem::getFoodPrice).sum();
//
//        CustomerOrder order = new CustomerOrder();
//        order.setCustomerName(orderDTO.getCustomerName());
//        order.setCustomerPhoneNumber(orderDTO.getCustomerPhoneNumber());
//        order.setCustomerTableNumber(orderDTO.getCustomerTableNumber());
//        order.setOrderItems(menuItems);
//        order.setTotalPrice(totalPrice);
//
//        return customerOrderRepository.save(order);
//    }
//
//    @Override
//    public CustomerOrder updateOrderById(Long id, CustomerOrderDTO orderDTO) {
//        CustomerOrder existingOrder = customerOrderRepository.findById(id)
//            .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
//
//        List<MenuItem> menuItems = menuItemRepository.findAllById(orderDTO.getOrderItems());
//
//        double totalPrice = menuItems.stream().mapToDouble(MenuItem::getFoodPrice).sum();
//
//        existingOrder.setCustomerName(orderDTO.getCustomerName());
//        existingOrder.setCustomerPhoneNumber(orderDTO.getCustomerPhoneNumber());
//        existingOrder.setCustomerTableNumber(orderDTO.getCustomerTableNumber());
//        existingOrder.setOrderItems(menuItems);
//        existingOrder.setTotalPrice(totalPrice);
//
//        return customerOrderRepository.save(existingOrder);
//    }
//
//    @Override
//    public void deleteOrder(Long id) {
//        CustomerOrder order = customerOrderRepository.findById(id)
//            .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
//        customerOrderRepository.delete(order);
//    }
//}


package com.cts.main.servicesImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.cts.main.dtos.CustomerOrderDTO;
import com.cts.main.entities.CustomerOrder;
import com.cts.main.entities.MenuItem;
import com.cts.main.entities.User;
import com.cts.main.repository.CustomerOrderRepository;
import com.cts.main.repository.MenuItemRepository;
import com.cts.main.repository.UserRepository;
import com.cts.main.services.CustomerOrderService;

@Service
public class CustomerOrderServiceImpl implements CustomerOrderService {

    @Autowired
    private CustomerOrderRepository customerOrderRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public CustomerOrder getOrderById(Long id) {
        CustomerOrder order = customerOrderRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));

        if (!isAdmin() && !order.getUser().getUsername().equals(getCurrentUsername())) {
            throw new RuntimeException("You do not have permission to view this order");
        }
        return order;
    }

    @Override
    public CustomerOrder createOrder(CustomerOrderDTO orderDTO) {
        List<MenuItem> menuItems = menuItemRepository.findAllById(orderDTO.getOrderItems());
        double totalPrice = menuItems.stream().mapToDouble(MenuItem::getFoodPrice).sum();

        CustomerOrder order = new CustomerOrder();
        order.setCustomerName(orderDTO.getCustomerName());
        order.setCustomerPhoneNumber(orderDTO.getCustomerPhoneNumber());
        order.setCustomerTableNumber(orderDTO.getCustomerTableNumber());
        order.setOrderItems(menuItems);
        order.setTotalPrice(totalPrice);

        User user = getCurrentUser();
        order.setUser(user);

        return customerOrderRepository.save(order);
    }

    @Override
    public CustomerOrder updateOrderById(Long id, CustomerOrderDTO orderDTO) {
        CustomerOrder existingOrder = customerOrderRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));

        if (!isAdmin() && !existingOrder.getUser().getUsername().equals(getCurrentUsername())) {
            throw new RuntimeException("You do not have permission to edit this order");
        }

        List<MenuItem> menuItems = menuItemRepository.findAllById(orderDTO.getOrderItems());
        double totalPrice = menuItems.stream().mapToDouble(MenuItem::getFoodPrice).sum();

        existingOrder.setCustomerName(orderDTO.getCustomerName());
        existingOrder.setCustomerPhoneNumber(orderDTO.getCustomerPhoneNumber());
        existingOrder.setCustomerTableNumber(orderDTO.getCustomerTableNumber());
        existingOrder.setOrderItems(menuItems);
        existingOrder.setTotalPrice(totalPrice);

        return customerOrderRepository.save(existingOrder);
    }

    @Override
    public void deleteOrder(Long id) {
        CustomerOrder order = customerOrderRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
        customerOrderRepository.delete(order);
    }

    private String getCurrentUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }

    private boolean isAdmin() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            return userDetails.getAuthorities().stream()
                    .anyMatch(grantedAuthority -> "ROLE_ADMIN".equals(grantedAuthority.getAuthority()));
        }
        return false;
    }

    private User getCurrentUser() {
        return userRepository.findByUsername(getCurrentUsername())
            .orElseThrow(() -> new RuntimeException("User not found: " + getCurrentUsername()));
    }

    @Override
    public List<CustomerOrder> getAllOrders() {
        return customerOrderRepository.findAll();
    }
}
