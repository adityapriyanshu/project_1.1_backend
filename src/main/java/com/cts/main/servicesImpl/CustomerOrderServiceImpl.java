//package com.cts.main.servicesImpl;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.cts.main.entities.CustomerOrder;
//import com.cts.main.entities.MenuItem;
//import com.cts.main.repository.CustomerOrderRepository;
//import com.cts.main.repository.MenuItemRepository;
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
//	@Override
//	public List<CustomerOrder> getAllOrders() {
//		return customerOrderRepository.findAll();
//	}
//
//	@Override
//	public CustomerOrder getOrderById(Long id) {
//
//		return customerOrderRepository.findById(id).orElse(null);
//	}
//
//	@Override
//	public CustomerOrder createOrder(CustomerOrder order) {
//
//		// Fetch MenuItems from database using IDs
//		List<MenuItem> menuItems = menuItemRepository
//				.findAllById(order.getOrderItems().stream().map(MenuItem::getId).toList());
//
//		// Set the fetched MenuItems to the order
//		order.setOrderItems(menuItems);
//
//		// Calculate and set the total price
//		double totalPrice = menuItems.stream().mapToDouble(MenuItem::getFoodPrice).sum();
//		order.setTotalPrice(totalPrice);
//
//		return customerOrderRepository.save(order);
//	}
//	
//	
//	
//
//
//
//	@Override
//	public CustomerOrder updateOrderById(Long id, CustomerOrder updatedOrderDetails) {
//		CustomerOrder existingOrder = customerOrderRepository.findById(id)
//				.orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
//
//		// Update the fields with new data
//		existingOrder.setCustomerName(updatedOrderDetails.getCustomerName());
//		existingOrder.setCustomerPhoneNumber(updatedOrderDetails.getCustomerPhoneNumber());
//		existingOrder.setCustomerTableNumber(updatedOrderDetails.getCustomerTableNumber());
//
//		// Update order items
//		List<Long> itemIds = updatedOrderDetails.getOrderItems().stream().map(MenuItem::getId).toList();
//		List<MenuItem> menuItems = menuItemRepository.findAllById(itemIds);
//		existingOrder.setOrderItems(menuItems);
//
//		// Calculate and set the total price
//		double totalPrice = menuItems.stream().mapToDouble(MenuItem::getFoodPrice).sum();
//		existingOrder.setTotalPrice(totalPrice);
//
//		// Save the updated order
//		return customerOrderRepository.save(existingOrder);
//	}
//	
//	
//	
//
//
//
//	@Override
//	public void deleteOrder(Long id) {
//
//		CustomerOrder order = customerOrderRepository.findById(id).orElse(null);
//		if (order != null) {
//			customerOrderRepository.deleteById(id);
//		} else {
//			throw new RuntimeException("Order not found with id: " + id);
//		}
//	}
//
//}



package com.cts.main.servicesImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.main.dtos.CustomerOrderDTO;
import com.cts.main.entities.CustomerOrder;
import com.cts.main.entities.MenuItem;
import com.cts.main.repository.CustomerOrderRepository;
import com.cts.main.repository.MenuItemRepository;
import com.cts.main.services.CustomerOrderService;

@Service
public class CustomerOrderServiceImpl implements CustomerOrderService {

    @Autowired
    private CustomerOrderRepository customerOrderRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Override
    public List<CustomerOrder> getAllOrders() {
        return customerOrderRepository.findAll();
    }

    @Override
    public CustomerOrder getOrderById(Long id) {
        return customerOrderRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
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

        return customerOrderRepository.save(order);
    }

    @Override
    public CustomerOrder updateOrderById(Long id, CustomerOrderDTO orderDTO) {
        CustomerOrder existingOrder = customerOrderRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));

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
}
