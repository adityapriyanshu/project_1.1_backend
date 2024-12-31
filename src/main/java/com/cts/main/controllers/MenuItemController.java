//package com.cts.main.controllers;
//
//import java.util.List;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//
//import com.cts.main.dtos.MenuItemDTO;
//import com.cts.main.entities.MenuItem;
//import com.cts.main.services.MenuItemService;
//
//import jakarta.validation.Valid;
//
//@RestController
//@RequestMapping("/menu")
//public class MenuItemController {
//
//    @Autowired
//    private MenuItemService menuItemService;
//
//    @PostMapping("/add")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public ResponseEntity<?> addFoodItem(@Valid @RequestBody MenuItemDTO menuItemDTO) {
//        MenuItem menuItem = menuItemService.addFoodItem(menuItemDTO);
//        return ResponseEntity.ok(menuItem);
//    }
//
//    @GetMapping("/view")
//    public List<MenuItem> getAllItems() {
//        return menuItemService.getAllItems();
//    }
//
//    @PutMapping("/update/{id}")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public ResponseEntity<?> updateMenuItem(@PathVariable Long id, @Valid @RequestBody MenuItemDTO menuItemDTO) {
//        MenuItem updateMenuItem = menuItemService.updateFoodItemById(id, menuItemDTO);
//        return ResponseEntity.ok(updateMenuItem);
//    }
//
//    @DeleteMapping("/delete/{id}")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public ResponseEntity<?> deleteMenuItem(@PathVariable Long id) {
//        menuItemService.deleteFoodItemById(id);
//        return ResponseEntity.ok().build();
//    }
//}
package com.cts.main.controllers;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.cts.main.responses.ApiResponse;
import com.cts.main.dtos.MenuItemDTO;
import com.cts.main.entities.MenuItem;
import com.cts.main.services.MenuItemService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/menu")
public class MenuItemController {

    private static final Logger logger = LoggerFactory.getLogger(MenuItemController.class);

    @Autowired
    private MenuItemService menuItemService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> addFoodItem(@Valid @RequestBody MenuItemDTO menuItemDTO) {
        logger.info("Adding new food item: {}", menuItemDTO.getFoodName());
        MenuItem menuItem = menuItemService.addFoodItem(menuItemDTO);
        ApiResponse<MenuItem> response = new ApiResponse<>("Item added successfully!", menuItem);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/view")
    public ResponseEntity<?> getAllItems() {
        logger.info("Fetching all menu items");
        List<MenuItem> items = menuItemService.getAllItems();
        ApiResponse<List<MenuItem>> response = new ApiResponse<>("Items fetched successfully!", items);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> updateMenuItem(@PathVariable Long id, @Valid @RequestBody MenuItemDTO menuItemDTO) {
        logger.info("Updating menu item with ID: {}", id);
        MenuItem updateMenuItem = menuItemService.updateFoodItemById(id, menuItemDTO);
        ApiResponse<MenuItem> response = new ApiResponse<>("Item updated successfully!", updateMenuItem);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteMenuItem(@PathVariable Long id) {
        logger.info("Deleting menu item with ID: {}", id);
        menuItemService.deleteFoodItemById(id);
        ApiResponse<String> response = new ApiResponse<>("Item deleted successfully!", null);
        return ResponseEntity.ok(response);
    }
}
