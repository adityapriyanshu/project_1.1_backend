package com.cts.main.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "customer_order")
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

	@ManyToMany
	@JoinTable(name = "customer_order_with_order_items", 
	joinColumns = @JoinColumn(name = "customer_order_id"), 
	inverseJoinColumns = @JoinColumn(name = "order_items_id"))
	private List<MenuItem> orderItems;

//	@ElementCollection
//	@CollectionTable(name = "customer_order_items", joinColumns = @JoinColumn(name = "customer_order_id"))
//	@MapKeyJoinColumn(name = "menu_item_id")
//	@Column(name = "quantity")
//	private Map<MenuItem, Integer> orderItems; // Map to keep track of MenuItem and its quantity

	@Column(name = "total_price")
	private double totalPrice;
}
