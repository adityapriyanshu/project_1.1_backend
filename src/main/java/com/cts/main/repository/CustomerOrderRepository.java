package com.cts.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.main.entities.CustomerOrder;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long>{
	
}
