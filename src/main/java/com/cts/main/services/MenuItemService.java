package com.cts.main.services;

import java.util.List;

import com.cts.main.entities.MenuItem;

public interface MenuItemService {
	
	public List<MenuItem> getAllItems();
	
	public MenuItem addFoodItem(MenuItem menuItem);
	
	public MenuItem updateFoodItemById(Long id, MenuItem menuitem);
	
	public void deleteFoodItemById(Long id);
	
}
