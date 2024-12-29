//package com.cts.main.servicesImpl;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import com.cts.main.entities.User;
//import com.cts.main.repository.UserRepository;
//import com.cts.main.services.UserService;
//
//@Service
//public class UserServiceImpl implements UserService {
//
//	@Autowired
//	private UserRepository userRepository;
//	
//	@Autowired
//	private PasswordEncoder password;
//	
//	@Override
//	public User adduser(User user) {
//		// TODO Auto-generated method stub
//		user.setPassword(password.encode(user.getPassword()));
//		return userRepository.save(user);
//	}
//	
//	@Override
//	public List<User> getuser() {
//		return userRepository.findAll();
//	}
//
//	@Override
//	public ResponseEntity<String> updateuser(User user) {
////		User user1 =User.builder()
////				.username(user.getUsername())
////				.password(password.encode(user.getPassword()))
////				.Roles(user.getRoles())
////				.build();
//		if(userRepository.existsById(user.getUser_id())) {
//			userRepository.save(user);
//		}
//		return ResponseEntity.status(HttpStatus.OK).body("User updated successfully");
//	}
//
//}

package com.cts.main.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cts.main.dtos.UserDTO;
import com.cts.main.entities.User;
import com.cts.main.repository.UserRepository;
import com.cts.main.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public ResponseEntity<?> adduser(UserDTO userDTO) {
		try {
			// Check if the username already exists
			if (userRepository.existsByUsername(userDTO.getUsername())) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists! Try different.");
			}
			// Proceed with saving the new user if username is unique
			User user = new User(userDTO.getUsername(), passwordEncoder.encode(userDTO.getPassword()),
					userDTO.getRoles());
			User createdUser = userRepository.save(user);
			return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
		} catch (DataIntegrityViolationException ex) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists! Try different.");
		}
	}

	@Override
	public List<User> getuser() {
		return userRepository.findAll();
	}

	@Override
	public ResponseEntity<String> updateuser(UserDTO userDTO) {

		// finding user by user id. If not found call else statement
		Optional<User> optionalUser = userRepository.findById(userDTO.getUser_id());
		if (optionalUser.isPresent()) {
			User user = optionalUser.get();

			// Check if the new username already exists and is not the current user's
			// username
			if (!user.getUsername().equals(userDTO.getUsername())
					&& userRepository.existsByUsername(userDTO.getUsername())) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists! Try different.");
			}
			user.setUsername(userDTO.getUsername());
			user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
			user.setRoles(userDTO.getRoles());
			userRepository.save(user);
			return ResponseEntity.status(HttpStatus.OK).body("User updated successfully!");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
		}
	}
}
