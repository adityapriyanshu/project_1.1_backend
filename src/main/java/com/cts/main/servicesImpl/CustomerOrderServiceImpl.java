package com.cts.main.servicesImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cts.main.entities.CustomerOrder;
import com.cts.main.repository.CustomerOrderRepository;
import com.cts.main.services.CustomerOrderService;

@Service
public class CustomerOrderServiceImpl implements CustomerOrderService {

	private CustomerOrderRepository customerOrderRepository;
	
	@Override
	public List<CustomerOrder> getAllOrders() {
		return customerOrderRepository.findAll();
	}

	@Override
	public CustomerOrder getOrderById(Long id) {
		
		return customerOrderRepository.findById(id).orElse(null);
	}

	@Override
	public CustomerOrder createOder(CustomerOrder order) {
		double totalPrice = order.getOrderItems().stream().mapToDouble(food -> food.getFoodPrice()).sum();
		order.setTotalPrice(totalPrice);
		return null;
	}

	@Override
	public void deleteOrder(Long id) {
		// TODO Auto-generated method stub
		
	}
	
	
}
