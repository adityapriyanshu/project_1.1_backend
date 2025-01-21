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

//package com.cts.main.servicesImpl;
//
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Service;
//
//import com.cts.main.dtos.CustomerOrderDTO;
//import com.cts.main.entities.CustomerOrder;
//import com.cts.main.entities.MenuItem;
//import com.cts.main.entities.User;
//import com.cts.main.repository.CustomerOrderRepository;
//import com.cts.main.repository.MenuItemRepository;
//import com.cts.main.repository.UserRepository;
//import com.cts.main.services.CustomerOrderService;
//
//@Service
//public class CustomerOrderServiceImpl implements CustomerOrderService {
//
//	@Autowired
//	private CustomerOrderRepository customerOrderRepository;
//
//	@Autowired
//	private MenuItemRepository menuItemRepository;
//
//	@Autowired
//	private UserRepository userRepository;
//
//	@Override
//	public CustomerOrder getOrderById(Long id) {
//		CustomerOrder order = customerOrderRepository.findById(id)
//				.orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
//
//		if (!isAdmin() && !order.getUser().getUsername().equals(getCurrentUsername())) {
//			throw new RuntimeException("You do not have permission to view this order");
//		}
//		return order;
//	}
//
//	@Override
//	public CustomerOrder createOrder(CustomerOrderDTO orderDTO) {
//		List<MenuItem> menuItems = menuItemRepository.findAllById(orderDTO.getOrderItems());
//		double totalPrice = menuItems.stream().mapToDouble(MenuItem::getFoodPrice).sum();
//
//		CustomerOrder order = new CustomerOrder();
//		order.setCustomerName(orderDTO.getCustomerName());
//		order.setCustomerPhoneNumber(orderDTO.getCustomerPhoneNumber());
//		order.setCustomerTableNumber(orderDTO.getCustomerTableNumber());
//		order.setOrderItems(menuItems);
//		order.setTotalPrice(totalPrice);
//
//		User user = getCurrentUser();
//		order.setUser(user);
//
//		return customerOrderRepository.save(order);
//	}
//
//	@Override
//	public CustomerOrder updateOrderById(Long id, CustomerOrderDTO orderDTO) {
//		CustomerOrder existingOrder = customerOrderRepository.findById(id)
//				.orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
//
//		if (!isAdmin() && !existingOrder.getUser().getUsername().equals(getCurrentUsername())) {
//			throw new RuntimeException("You do not have permission to edit this order");
//		}
//
//		List<MenuItem> menuItems = menuItemRepository.findAllById(orderDTO.getOrderItems());
//		double totalPrice = menuItems.stream().mapToDouble(MenuItem::getFoodPrice).sum();
//
//		existingOrder.setCustomerName(orderDTO.getCustomerName());
//		existingOrder.setCustomerPhoneNumber(orderDTO.getCustomerPhoneNumber());
//		existingOrder.setCustomerTableNumber(orderDTO.getCustomerTableNumber());
//		existingOrder.setOrderItems(menuItems);
//		existingOrder.setTotalPrice(totalPrice);
//
//		return customerOrderRepository.save(existingOrder);
//	}
//
//	@Override
//	public void deleteOrder(Long id) {
//		CustomerOrder order = customerOrderRepository.findById(id)
//				.orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
//		customerOrderRepository.delete(order);
//	}
//
//	private String getCurrentUsername() {
//		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		if (principal instanceof UserDetails) {
//			return ((UserDetails) principal).getUsername();
//		} else {
//			return principal.toString();
//		}
//	}
//
//	private boolean isAdmin() {
//		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		if (principal instanceof UserDetails) {
//			UserDetails userDetails = (UserDetails) principal;
//			return userDetails.getAuthorities().stream()
//					.anyMatch(grantedAuthority -> "ROLE_ADMIN".equals(grantedAuthority.getAuthority()));
//		}
//		return false;
//	}
//
//	private User getCurrentUser() {
//		return userRepository.findByUsername(getCurrentUsername())
//				.orElseThrow(() -> new RuntimeException("User not found: " + getCurrentUsername()));
//	}
//
//	@Override
//	public List<CustomerOrder> getAllOrders() {
//		return customerOrderRepository.findAll();
//	}
//
//	@Override
//	public List<Map<String, Object>> getAllOrdersForCooks() {
//
//		List<CustomerOrder> orders = customerOrderRepository.findAll();
//		return orders.stream().map(order -> {
//			Map<String, Object> orderMap = new LinkedHashMap<>();
//			orderMap.put("id", order.getId());
//			orderMap.put("customerName", order.getCustomerName());
//			orderMap.put("customerTableNumber", order.getCustomerTableNumber());
//			orderMap.put("orderItems", order.getOrderItems().stream().map(item -> {
//				Map<String, Object> itemMap = new LinkedHashMap<>();
//				itemMap.put("id", item.getId());
//				itemMap.put("foodName", item.getFoodName());
//				return itemMap;
//			}).collect(Collectors.toList()));
//			return orderMap;
//		}).collect(Collectors.toList());
//	}
//}

package com.cts.main.servicesImpl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
    
//    @Override
//    public CustomerOrder getOrderByUserName(String username) {
//    	CustomerOrder order = customerOrderRepository.findByUsername(username);
//    	  if (order != null) {
//    		    return order;
//    		  } else {
//    		    throw new RuntimeException("Order not found for username: " + username);
//    		  }
//    		}
    
    public List<CustomerOrder> getOrdersByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return customerOrderRepository.findByUser(user.get());
        } else {
            throw new RuntimeException("User not found with username: " + username);
        }
    }

    @Override
    public CustomerOrder createOrder(CustomerOrderDTO orderDTO) {
        validateOrderItems(orderDTO.getOrderItems());

        List<MenuItem> menuItems = menuItemRepository.findAllById(orderDTO.getOrderItems().keySet());

        List<MenuItem> orderItems = orderDTO.getOrderItems().entrySet().stream()
            .flatMap(entry -> {
                MenuItem menuItem = menuItems.stream()
                    .filter(item -> item.getId().equals(entry.getKey()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("MenuItem not found with id: " + entry.getKey()));
                return java.util.stream.Stream.generate(() -> menuItem).limit(entry.getValue());
            })
            .collect(Collectors.toList());

        double totalPrice = orderItems.stream().mapToDouble(MenuItem::getFoodPrice).sum();

        CustomerOrder order = new CustomerOrder();
        order.setCustomerName(orderDTO.getCustomerName());
        order.setCustomerPhoneNumber(orderDTO.getCustomerPhoneNumber());
        order.setCustomerAddress(orderDTO.getCustomerAddress());
        order.setOrderItems(orderItems);
        order.setTotalPrice(totalPrice);

        User user = getCurrentUser();
        order.setUser(user);

        return customerOrderRepository.save(order);
    }

    @Override
    public CustomerOrder updateOrderById(Long id, CustomerOrderDTO orderDTO) {
        validateOrderItems(orderDTO.getOrderItems());

        CustomerOrder existingOrder = customerOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));

        if (!isAdmin() && !existingOrder.getUser().getUsername().equals(getCurrentUsername())) {
            throw new RuntimeException("You do not have permission to edit this order");
        }

        List<MenuItem> menuItems = menuItemRepository.findAllById(orderDTO.getOrderItems().keySet());

        List<MenuItem> orderItems = orderDTO.getOrderItems().entrySet().stream()
            .flatMap(entry -> {
                MenuItem menuItem = menuItems.stream()
                    .filter(item -> item.getId().equals(entry.getKey()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("MenuItem not found with id: " + entry.getKey()));
                return java.util.stream.Stream.generate(() -> menuItem).limit(entry.getValue());
            })
            .collect(Collectors.toList());

        double totalPrice = orderItems.stream().mapToDouble(MenuItem::getFoodPrice).sum();

        existingOrder.setCustomerName(orderDTO.getCustomerName());
        existingOrder.setCustomerPhoneNumber(orderDTO.getCustomerPhoneNumber());
        existingOrder.setCustomerAddress(orderDTO.getCustomerAddress());
        existingOrder.setOrderItems(orderItems);
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

    @Override
    public List<Map<String, Object>> getAllOrdersForCooks() {
        List<CustomerOrder> orders = customerOrderRepository.findAll();
        return orders.stream().map(order -> {
            Map<String, Object> orderMap = new LinkedHashMap<>();
            orderMap.put("id", order.getId());
            orderMap.put("customerName", order.getCustomerName());
            orderMap.put("customerTableNumber", order.getCustomerAddress());
            
            
            orderMap.put("orderItems", order.getOrderItems().stream()
                .collect(Collectors.groupingBy(MenuItem::getId))
                .entrySet().stream().map(entry -> {
                	
                    Map<String, Object> itemMap = new LinkedHashMap<>();
                    itemMap.put("id", entry.getKey());
                    itemMap.put("foodName", entry.getValue().get(0).getFoodName());
                    itemMap.put("quantity", entry.getValue().size());
                    return itemMap;
                    
                    
                }).collect(Collectors.toList()));
            return orderMap;
        }).collect(Collectors.toList());
    }

    private void validateOrderItems(Map<Long, Integer> orderItems) {
        for (Map.Entry<Long, Integer> entry : orderItems.entrySet()) {
            if (entry.getValue() > 5) {
                throw new IllegalArgumentException("Quantity for item ID " + entry.getKey() + " exceeds the maximum allowed limit of 5");
            }
        }
    }
}
