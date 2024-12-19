package com.cts.main.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cts.main.entities.User;
import com.cts.main.repository.UserRepository;
import com.cts.main.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder password;
	
	@Override
	public User adduser(User user) {
		// TODO Auto-generated method stub
		user.setPassword(password.encode(user.getPassword()));
		return userRepository.save(user);
	}
	
	@Override
	public List<User> getuser() {
		return userRepository.findAll();
	}

	@Override
	public ResponseEntity<String> updateuser(User user) {
//		User user1 =User.builder()
//				.username(user.getUsername())
//				.password(password.encode(user.getPassword()))
//				.Roles(user.getRoles())
//				.build();
		if(userRepository.existsById(user.getUser_id())) {
			userRepository.save(user);
		}
		return ResponseEntity.status(HttpStatus.OK).body("User updated successfully");
	}

}
