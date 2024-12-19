//package com.cts.main.loaders;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//
//import com.cts.main.entities.MenuItem;
//import com.cts.main.repository.MenuItemRepository;
//
//public class MenuItemsDataLoaders implements CommandLineRunner{
//
//	@Autowired
//	private MenuItemRepository menuItemRepository;
//	
//	@Override
//	public void run(String... args) throws Exception {
//		
//		menuItemRepository.save(new MenuItem(null, "Burger", 5.99));
//		menuItemRepository.save(new MenuItem(null, "Pizza", 8.25));
//		menuItemRepository.save(new MenuItem(null, "Salad", 5.00));
//		menuItemRepository.save(new MenuItem(null, "Sushi", 12.00));
//		System.out.println("Menu Items have been initialized!");
//	}
//
//	
//}
