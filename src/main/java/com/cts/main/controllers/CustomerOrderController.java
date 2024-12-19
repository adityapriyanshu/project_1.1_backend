//package com.cts.main.controllers;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.cts.main.entities.CustomerOrder;
//import com.cts.main.services.CustomerOrderService;
//
//@RestController
//public class CustomerOrderController {
//	
//	@Autowired
//	private CustomerOrderService customerOrderService;
//	
//	
//	// View All CustomerOrder Details
//	@GetMapping("/customer")
//	public ResponseEntity<List<CustomerOrder>> getAllCustomerOrder(){
//		List<CustomerOrder> list = customerOrderService.getAllOrders();
//		
//		if(list.size() != 0) {
//			return ResponseEntity.ok(list);
//		}
//		else {
//			return ResponseEntity.notFound().build();
//		}
//	}
//	
//	
//	// View a CustomerOrder by ID
//	@GetMapping("/customer/{id}")
//	public ResponseEntity<CustomerOrder> getCustomerOrderById(@PathVariable Long id){
//		CustomerOrder order = customerOrderService.getOrderById(id);
//		
//		if(order != null) {
//			return ResponseEntity.ok(order);
//		}
//		else {
//			return ResponseEntity.notFound().build();
//		}
//	}
//	
//	
//	// Add a CustomerOrder Details
//	@PostMapping("/customer")
//	public ResponseEntity<CustomerOrder> addCustomerOrder(@RequestBody CustomerOrder orderDetails){
//		CustomerOrder order = customerOrderService.createOrder(orderDetails);
//		
//		if(order != null) {
//			return ResponseEntity.ok(order);
//		}
//		else {
//			return ResponseEntity.notFound().build();
//		}
//	}
//	
//	
//	// Edit Order Details by Id
//	@PutMapping("/customer/{id}")
//	public ResponseEntity<CustomerOrder> updateCustomerOrderById(@PathVariable Long id, @RequestBody CustomerOrder order){
//		try {
//			CustomerOrder updatedOrder = customerOrderService.updateOrderById(id, order);
//			return ResponseEntity.ok(updatedOrder);
//		} catch (RuntimeException e) {
//			return ResponseEntity.notFound().build();
//		}
//	}
//	
//	
//	// Delete CustomerOrder by id
//	@DeleteMapping("/customer/{id}")
//	public ResponseEntity<Void> deleteCustomerOrderById(@PathVariable Long id){
//		try {
//			customerOrderService.deleteOrder(id);
//			return ResponseEntity.noContent().build(); // Return 204 No Content if deletion is successful
//			
//		} catch (RuntimeException e) {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//		}
//	}
//	
//}



package com.cts.main.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.cts.main.dtos.CustomerOrderDTO;
import com.cts.main.entities.CustomerOrder;
import com.cts.main.services.CustomerOrderService;
import jakarta.validation.Valid;

@RestController
public class CustomerOrderController {

    @Autowired
    private CustomerOrderService customerOrderService;

    @GetMapping("/customer")//admin
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<CustomerOrder>> getAllCustomerOrder() {
        List<CustomerOrder> list = customerOrderService.getAllOrders();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/customer/{id}")//admin
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<CustomerOrder> getCustomerOrderById(@PathVariable Long id) {
        CustomerOrder order = customerOrderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    @PostMapping("/customer")
    @PreAuthorize("hasRole('ROLE_CUSTOMER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> addCustomerOrder(@Valid @RequestBody CustomerOrderDTO orderDetails) {
        customerOrderService.createOrder(orderDetails);
        return new ResponseEntity<>("Customer Order created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/customer/{id}")
    @PreAuthorize("hasRole('ROLE_CUSTOMER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> updateCustomerOrderById(@PathVariable Long id, @Valid @RequestBody CustomerOrderDTO orderDetails) {
        customerOrderService.updateOrderById(id, orderDetails);
        return ResponseEntity.ok("Customer Order updated successfully");
    }

    @DeleteMapping("/customer/{id}")//admin
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> deleteCustomerOrderById(@PathVariable Long id) {
        customerOrderService.deleteOrder(id);
        return ResponseEntity.ok("Customer Order deleted successfully");
    }
}
