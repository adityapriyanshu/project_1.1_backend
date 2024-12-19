package com.cts.main.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cts.main.entities.User;

public interface UserService {

	User adduser(User user);

	List<User> getuser();
	ResponseEntity<String> updateuser(User user);

}
