package com.cts.main.servicesImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.main.dtos.MenuItemDTO;
import com.cts.main.entities.MenuItem;
import com.cts.main.repository.MenuItemRepository;
import com.cts.main.services.MenuItemService;

@Service
public class MenuItemServiceImpl implements MenuItemService {

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Override
    public MenuItem addFoodItem(MenuItemDTO menuItemDTO) {
        MenuItem menuItem = new MenuItem(menuItemDTO.getFoodName(), menuItemDTO.getFoodPrice());
        return menuItemRepository.save(menuItem);
    }

    @Override
    public List<MenuItem> getAllItems() {
        return menuItemRepository.findAll();
    }

    @Override
    public MenuItem updateFoodItemById(Long id, MenuItemDTO menuItemDTO) {
        MenuItem existingItem = menuItemRepository.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));
        existingItem.setFoodName(menuItemDTO.getFoodName());
        existingItem.setFoodPrice(menuItemDTO.getFoodPrice());
        return menuItemRepository.save(existingItem);
    }

    @Override
    public void deleteFoodItemById(Long id) {
    	menuItemRepository.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));
        menuItemRepository.deleteById(id);
    }
}
