package com.cts.main.services;

import java.util.List;
import java.util.Map;

import com.cts.main.dtos.CustomerOrderDTO;
import com.cts.main.entities.CustomerOrder;

//public interface CustomerOrderService {
//	
//	public List<CustomerOrder> getAllOrders();
//	
//	public CustomerOrder getOrderById(Long id);
//	
//	public CustomerOrder createOrder(CustomerOrder order);
//	
//	public CustomerOrder updateOrderById(Long id, CustomerOrder order);
//	
//	void deleteOrder(Long id);
//}
public interface CustomerOrderService {
	List<CustomerOrder> getAllOrders();

	CustomerOrder getOrderById(Long id);

	CustomerOrder createOrder(CustomerOrderDTO orderDTO);

	CustomerOrder updateOrderById(Long id, CustomerOrderDTO orderDTO);

	void deleteOrder(Long id);

	List<Map<String, Object>> getAllOrdersForCooks();

	List<CustomerOrder> getOrdersByUsername(String username);

//	CustomerOrder getOrderByUserName(String username);
}
