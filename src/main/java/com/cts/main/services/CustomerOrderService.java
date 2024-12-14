package com.cts.main.services;

import java.util.List;

import com.cts.main.entities.CustomerOrder;

public interface CustomerOrderService {
	
	public List<CustomerOrder> getAllOrders();
	
	public CustomerOrder getOrderById(Long id);
	
	public CustomerOrder createOder(CustomerOrder order);
	
	void deleteOrder(Long id);
}
