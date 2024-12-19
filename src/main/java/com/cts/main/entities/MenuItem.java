package com.cts.main.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
//import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
//@AllArgsConstructor
@NoArgsConstructor
@Table(name = "menu_items")
public class MenuItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;

	@Column(name = "food_name")
	private String foodName;

	@Column(name = "food_price")
	private double foodPrice;

	public MenuItem(String foodName, double foodPrice) {
		super();
		this.foodName = foodName;
		this.foodPrice = foodPrice;
	}

	
}
