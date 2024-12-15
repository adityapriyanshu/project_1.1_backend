package com.cts.main.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

		return customerOrderRepository.findById(id).orElse(null);
	}

	@Override
	public CustomerOrder createOrder(CustomerOrder order) {

		// Fetch MenuItems from database using IDs
		List<MenuItem> menuItems = menuItemRepository
				.findAllById(order.getOrderItems().stream().map(MenuItem::getId).toList());

		// Set the fetched MenuItems to the order
		order.setOrderItems(menuItems);

		// Calculate and set the total price
		double totalPrice = menuItems.stream().mapToDouble(MenuItem::getFoodPrice).sum();
		order.setTotalPrice(totalPrice);

		return customerOrderRepository.save(order);
	}


	@Override
	public CustomerOrder updateOrderById(Long id, CustomerOrder updatedOrderDetails) {
		CustomerOrder existingOrder = customerOrderRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Order not found with id: " + id));

		// Update the fields with new data
		existingOrder.setCustomerName(updatedOrderDetails.getCustomerName());
		existingOrder.setCustomerPhoneNumber(updatedOrderDetails.getCustomerPhoneNumber());
		existingOrder.setCustomerTableNumber(updatedOrderDetails.getCustomerTableNumber());

		// Update order items
		List<Long> itemIds = updatedOrderDetails.getOrderItems().stream().map(MenuItem::getId).toList();
		List<MenuItem> menuItems = menuItemRepository.findAllById(itemIds);
		existingOrder.setOrderItems(menuItems);

		// Calculate and set the total price
		double totalPrice = menuItems.stream().mapToDouble(MenuItem::getFoodPrice).sum();
		existingOrder.setTotalPrice(totalPrice);

		// Save the updated order
		return customerOrderRepository.save(existingOrder);
	}


	@Override
	public void deleteOrder(Long id) {

		CustomerOrder order = customerOrderRepository.findById(id).orElse(null);
		if (order != null) {
			customerOrderRepository.deleteById(id);
		} else {
			throw new RuntimeException("Order not found with id: " + id);
		}
	}

}

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
//		// Update order items and their quantities
//		Map<MenuItem, Integer> menuItemQuantities = updatedOrderDetails.getOrderItems().entrySet().stream()
//				.collect(Collectors.toMap(entry -> menuItemRepository.findById(entry.getKey().getId()).orElseThrow(),
//						Map.Entry::getValue));
//		existingOrder.setOrderItems(menuItemQuantities);
//
//		// Calculate and set the total price
//		double totalPrice = menuItemQuantities.entrySet().stream()
//				.mapToDouble(entry -> entry.getKey().getFoodPrice() * entry.getValue()).sum();
//		existingOrder.setTotalPrice(totalPrice);
//
//		// Save the updated order
//		return customerOrderRepository.save(existingOrder);
//	}



//	@Override
//	public CustomerOrder createOrder(CustomerOrder orderDetails) {
//		List<Long> itemIds = orderDetails.getOrderItems().stream().map(MenuItem::getId).toList();
//		List<MenuItem> menuItems = menuItemRepository.findAllById(itemIds);
//		orderDetails.setOrderItems(menuItems);
//
//		double totalPrice = menuItems.stream().mapToDouble(MenuItem::getFoodPrice).sum();
//		orderDetails.setTotalPrice(totalPrice);
//
//		return customerOrderRepository.save(orderDetails);
//	}

//	@Override
//	public CustomerOrder createOrder(CustomerOrder orderDetails) {
//		Map<MenuItem, Integer> menuItemQuantities = orderDetails.getOrderItems().entrySet().stream()
//				.collect(Collectors.toMap(entry -> menuItemRepository.findById(entry.getKey().getId()).orElseThrow(),
//						Map.Entry::getValue));
//		orderDetails.setOrderItems(menuItemQuantities);
//		double totalPrice = menuItemQuantities.entrySet().stream()
//				.mapToDouble(entry -> entry.getKey().getFoodPrice() * entry.getValue()).sum();
//		orderDetails.setTotalPrice(totalPrice);
//		return customerOrderRepository.save(orderDetails);
//	}