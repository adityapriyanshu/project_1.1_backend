package com.cts.main.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.main.entities.MenuItem;
import com.cts.main.repository.MenuItemRepository;
import com.cts.main.services.MenuItemService;

@Service
public class MenuItemServiceImpl implements MenuItemService {

	@Autowired
	private MenuItemRepository menuItemRepository;

	@Override
	public List<MenuItem> getAllItems() {
		return menuItemRepository.findAll();
	}

	@Override
	public MenuItem addFoodItem(MenuItem menuItem) {
		return menuItemRepository.save(menuItem);
	}

	@Override
	public MenuItem updateFoodItemById(Long id, MenuItem updateMenuItem) {
		Optional<MenuItem> menuItem = menuItemRepository.findById(id);
		if(menuItem.isPresent()){
			MenuItem item = menuItem.get();
			
			item.setFoodName(updateMenuItem.getFoodName());
			item.setFoodPrice(updateMenuItem.getFoodPrice());
			return menuItemRepository.save(item);
		}
		return null;
	}
	
	
	@Override
	public void deleteFoodItemById(Long id) {
		
		menuItemRepository.deleteById(id);
		
	}

}
