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
public class ApplicationDataLoader implements ApplicationRunner {

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
		
		if(menuItemRepository.count() == 0) {	
		
		MenuItem menu1 = new MenuItem("Veg Burger", 125.00);
		MenuItem menu2 = new MenuItem("Margarita Pizza", 480.00);
		MenuItem menu3 = new MenuItem("Salad Bowl", 75.00);
		MenuItem menu4 = new MenuItem("Sushi", 220.00);
		MenuItem menu5 = new MenuItem("Chole Bhature", 90.00);
		MenuItem menu6 = new MenuItem("Ice-cream", 45.00);
		menuItemRepository.saveAll(Arrays.asList(menu1, menu2, menu3, menu4, menu5, menu6));
		
//		
//		CustomerOrder order1 = new CustomerOrder("Aditya","7492919397", "Table 1", Arrays.asList(menu1, menu2), 13.00, customer);
//		customerOrderRepository.save(order1);
		}
		
		User admin = new User("Aditya", passwordEncoder.encode("1234"), "ROLE_ADMIN");
		userRepository.save(admin);
		
		User customer = new User("Priyanshu", passwordEncoder.encode("1234"), "ROLE_CUSTOMER");
		userRepository.save(customer);
	}

}
