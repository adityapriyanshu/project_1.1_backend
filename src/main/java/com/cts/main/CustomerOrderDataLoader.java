package com.cts.main;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.cts.main.dtos.CustomerOrderDTO;
import com.cts.main.entities.CustomerOrder;
import com.cts.main.entities.MenuItem;
import com.cts.main.entities.User;
import com.cts.main.repository.CustomerOrderRepository;
import com.cts.main.repository.MenuItemRepository;
import com.cts.main.repository.UserRepository;

@Component
public class CustomerOrderDataLoader implements ApplicationRunner {

	@Autowired
	private CustomerOrderRepository customerOrderRepository;
	
	@Autowired
	private MenuItemRepository menuItemRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
//		menuItemRepository.save(new MenuItem(null, "Burger", 5.00));
//		menuItemRepository.save(new MenuItem(null, "Pizza", 8.00));
//		menuItemRepository.save(new MenuItem(null, "Salad", 5.00));
//		menuItemRepository.save(new MenuItem(null, "Sushi", 12.00));
		
		if(menuItemRepository.count() == 0) {	
		
		MenuItem menu1 = new MenuItem("Burger", 5.00);
		MenuItem menu2 = new MenuItem("Pizza", 8.00);
		MenuItem menu3 = new MenuItem("Salad", 5.00);
		MenuItem menu4 = new MenuItem("Sushi", 12.00);
		menuItemRepository.saveAll(Arrays.asList(menu1, menu2, menu3, menu4));
		
		CustomerOrder order1 = new CustomerOrder("Aditya","7492919397", "Table 1", Arrays.asList(menu1, menu2), 13.00);
		customerOrderRepository.save(order1);
		}
		
		User admin = new User("Aditya", passwordEncoder.encode("12345678"), "ROLE_ADMIN");
		userRepository.save(admin);
	}

}
