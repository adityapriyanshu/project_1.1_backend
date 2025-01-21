package com.cts.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.main.entities.CustomerOrder;
import com.cts.main.entities.User;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long>{

	List<CustomerOrder> findByUser(User user);

//	CustomerOrder findByUsername(String username);
	
}
