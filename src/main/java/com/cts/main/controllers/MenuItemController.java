package com.cts.main.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cts.main.entities.MenuItem;
import com.cts.main.services.MenuItemService;

@RestController
@RequestMapping("/menu")
public class MenuItemController {
	@Autowired
	private MenuItemService menuItemService;

	@GetMapping("/view")
	public List<MenuItem> getAllItems() {
		return menuItemService.getAllItems();
	}

	@PostMapping("/add")//admin
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public MenuItem addFoodItem(@RequestBody MenuItem menuItem) {
		return menuItemService.addFoodItem(menuItem);
	}

	@PutMapping("/update/{id}")//admin
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<MenuItem> updateMenuItem(@PathVariable Long id, @RequestBody MenuItem menuItem) {
		MenuItem updateMenuItem = menuItemService.updateFoodItemById(id, menuItem);
		if (updateMenuItem != null) {
			return ResponseEntity.ok(updateMenuItem);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/delete/{id}")//admin
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public void deleteMenuItem(@PathVariable Long id){
		 menuItemService.deleteFoodItemById(id);
		 return ;
	}
	
}





//	@PutMapping("/update/{id}")
//	public ResponseEntity<MenuItem> updateMenuItem(@PathVariable int id, @RequestBody MenuItem menuItem) {
//		MenuItem updateMenuItem = menuItemService.updateFoodItemById(id, menuItem);
//		if (updateMenuItem != null) {
//			return ResponseEntity.ok(updateMenuItem);
//		} else {
//			return ResponseEntity.notFound().build();
//		}
//	}

//	@PutMapping("/{id}")
//	public MenuItem updateMenuItem(@PathVariable Long id, @RequestBody MenuItem menuItem) {
//		return menuItemService.updateFoodItemById(id, menuItem);
//		
//	}