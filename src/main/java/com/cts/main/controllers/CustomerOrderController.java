
//package com.cts.main.controllers;
//
//import java.util.List;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//import com.cts.main.dtos.CustomerOrderDTO;
//import com.cts.main.entities.CustomerOrder;
//import com.cts.main.services.CustomerOrderService;
//import jakarta.validation.Valid;
//
//@RestController
//public class CustomerOrderController {
//
//    @Autowired
//    private CustomerOrderService customerOrderService;
//
//    @GetMapping("/customer")//admin
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    public ResponseEntity<List<CustomerOrder>> getAllCustomerOrder() {
//        List<CustomerOrder> list = customerOrderService.getAllOrders();
//        return ResponseEntity.ok(list);
//    }
//
//    @GetMapping("/customer/{id}")//admin
//    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_CUSTOMER')")
//    public ResponseEntity<CustomerOrder> getCustomerOrderById(@PathVariable Long id) {
//        CustomerOrder order = customerOrderService.getOrderById(id);
//        return ResponseEntity.ok(order);
//    }
//
//    @PostMapping("/customer")
//    @PreAuthorize("hasRole('ROLE_CUSTOMER') or hasRole('ROLE_ADMIN')")
//    public ResponseEntity<String> addCustomerOrder(@Valid @RequestBody CustomerOrderDTO orderDetails) {
//        customerOrderService.createOrder(orderDetails);
//        return new ResponseEntity<>("Customer Order created successfully", HttpStatus.CREATED);
//    }
//
//    @PutMapping("/customer/{id}")
//    @PreAuthorize("hasRole('ROLE_CUSTOMER') or hasRole('ROLE_ADMIN')")
//    public ResponseEntity<String> updateCustomerOrderById(@PathVariable Long id, @Valid @RequestBody CustomerOrderDTO orderDetails) {
//        customerOrderService.updateOrderById(id, orderDetails);
//        return ResponseEntity.ok("Customer Order updated successfully");
//    }
//
//    @DeleteMapping("/customer/{id}")//admin
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    public ResponseEntity<String> deleteCustomerOrderById(@PathVariable Long id) {
//        customerOrderService.deleteOrder(id);
//        return ResponseEntity.ok("Customer Order deleted successfully");
//    }
//}

package com.cts.main.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.cts.main.dtos.CustomerOrderDTO;
import com.cts.main.entities.CustomerOrder;
import com.cts.main.services.CustomerOrderService;
import jakarta.validation.Valid;

import java.util.List;

@RestController
public class CustomerOrderController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerOrderController.class);

    @Autowired
    private CustomerOrderService customerOrderService;

    @GetMapping("/customer")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<CustomerOrder>> getAllCustomerOrder() {
        logger.info("Fetching all customer orders");
        List<CustomerOrder> list = customerOrderService.getAllOrders();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/customer/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_CUSTOMER')")
    public ResponseEntity<CustomerOrder> getCustomerOrderById(@PathVariable Long id) {
        logger.info("Fetching customer order with ID: {}", id);
        CustomerOrder order = customerOrderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    @PostMapping("/customer")
    @PreAuthorize("hasRole('ROLE_CUSTOMER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> addCustomerOrder(@Valid @RequestBody CustomerOrderDTO orderDetails) {
        logger.info("Creating a new customer order for customer: {}", orderDetails.getCustomerName());
        customerOrderService.createOrder(orderDetails);
        return new ResponseEntity<>("Customer Order created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/customer/{id}")
    @PreAuthorize("hasRole('ROLE_CUSTOMER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> updateCustomerOrderById(@PathVariable Long id, @Valid @RequestBody CustomerOrderDTO orderDetails) {
        logger.info("Updating customer order with ID: {}", id);
        customerOrderService.updateOrderById(id, orderDetails);
        return ResponseEntity.ok("Customer Order updated successfully");
    }

    @DeleteMapping("/customer/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> deleteCustomerOrderById(@PathVariable Long id) {
        logger.info("Deleting customer order with ID: {}", id);
        customerOrderService.deleteOrder(id);
        return ResponseEntity.ok("Customer Order deleted successfully");
    }
}

