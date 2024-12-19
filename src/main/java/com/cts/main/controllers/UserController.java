package com.cts.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.main.entities.User;
import com.cts.main.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/adduser")
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	public User createUser(@RequestBody User user) {
		return userService.adduser(user);
	}

	@PutMapping("/updateuser")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> updateUser(@RequestBody User user) {
		return userService.updateuser(user);
	}
	
	@GetMapping("/showuser")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<User> getuser(){
		return userService.getuser();
	}
}
