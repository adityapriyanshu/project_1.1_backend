package com.cts.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.main.entities.CustomerOrder;
import com.cts.main.services.CustomerOrderService;

@RestController
public class CustomerOrderController {
	
	@Autowired
	private CustomerOrderService customerOrderService;
	
	
	// View All CustomerOrder Details
	@GetMapping("/customer")
	public ResponseEntity<List<CustomerOrder>> getAllCustomerOrder(){
		List<CustomerOrder> list = customerOrderService.getAllOrders();
		
		if(list.size() != 0) {
			return ResponseEntity.ok(list);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	// View a CustomerOrder by ID
	@GetMapping("/customer/{id}")
	public ResponseEntity<CustomerOrder> getCustomerOrderById(@PathVariable Long id){
		CustomerOrder order = customerOrderService.getOrderById(id);
		
		if(order != null) {
			return ResponseEntity.ok(order);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	// Add a CustomerOrder Details
	@PostMapping("/customer")
	public ResponseEntity<CustomerOrder> addCustomerOrder(@RequestBody CustomerOrder orderDetails){
		CustomerOrder order = customerOrderService.createOder(orderDetails);
		
		if(order != null) {
			return ResponseEntity.ok(order);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
}
