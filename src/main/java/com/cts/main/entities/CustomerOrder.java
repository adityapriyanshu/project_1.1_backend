package com.cts.main.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="customer_order")
public class CustomerOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;
	
	@Column(name = "customer_name")
	private String customerName;
	
	@Column(name = "customer_phone_number")
	private String customerPhoneNumber;
	
	@Column(name = "customer_table_number")
	private String customerTableNumber;
	
	@OneToMany
	@Column(name = "order_items")
	private List<MenuItem> orderItems;
	
	@Column(name = "total_price")
	private double totalPrice;
}
