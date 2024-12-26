//package com.cts.main.controllers;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.cts.main.entities.User;
//import com.cts.main.services.UserService;
//
//@RestController
//@RequestMapping("/user")
//public class UserController {
//
//	@Autowired
//	private UserService userService;
//
//	@PostMapping("/adduser")
//	//@PreAuthorize("hasRole('ROLE_ADMIN')")
//	public User createUser(@RequestBody User user) {
//		return userService.adduser(user);
//	}
//
//	@PutMapping("/updateuser")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
//	public ResponseEntity<String> updateUser(@RequestBody User user) {
//		return userService.updateuser(user);
//	}
//	
//	@GetMapping("/showuser")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
//	public List<User> getuser(){
//		return userService.getuser();
//	}
//}

//
//package com.cts.main.controllers;
//
//import java.util.List;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//import com.cts.main.dtos.UserDTO;
//import com.cts.main.entities.User;
//import com.cts.main.services.UserService;
//
//import jakarta.validation.Valid;
//
//@RestController
//@RequestMapping("/user")
//@Validated
//public class UserController {
//
//    @Autowired
//    private UserService userService;
//
//    @PostMapping("/adduser")
//    public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO userDTO) {
//        if ("ROLE_ADMIN".equals(userDTO.getRoles())) {
//            if (!isAdmin()) {
//                return ResponseEntity.status(403).body("You do not have permission to create an admin user.");
//            }
//        }
////        User createdUser = userService.adduser(userDTO);
////        return ResponseEntity.ok(createdUser);
//        return userService.adduser(userDTO);
//    }
//
//    @PutMapping("/updateuser")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public ResponseEntity<?> updateUser(@Valid @RequestBody UserDTO userDTO) {
//        return userService.updateuser(userDTO);
//    }
//
//    @GetMapping("/showuser")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public List<User> getuser() {
//        return userService.getuser();
//    }
//
//    private boolean isAdmin() {
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if (principal instanceof UserDetails) {
//            UserDetails userDetails = (UserDetails) principal;
//            return userDetails.getAuthorities().stream()
//                    .anyMatch(grantedAuthority -> "ROLE_ADMIN".equals(grantedAuthority.getAuthority()));
//        }
//        return false;
//    }
//}


package com.cts.main.controllers;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.cts.main.dtos.UserDTO;
import com.cts.main.entities.User;
import com.cts.main.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/adduser")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO userDTO) {
        logger.info("Attempting to create user with username: {}", userDTO.getUsername());
        if ("ROLE_ADMIN".equals(userDTO.getRoles())) {
            if (!isAdmin()) {
                logger.info("Permission denied: User does not have permission to create an admin user.");
                return ResponseEntity.status(403).body("You do not have permission to create an admin user.");
            }
        }
        ResponseEntity<?> response = userService.adduser(userDTO);
        logger.info("User created successfully with username: {}", userDTO.getUsername());
        return response;
    }

    @PutMapping("/updateuser")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UserDTO userDTO) {
        logger.info("Attempting to update user with ID: {}", userDTO.getUser_id());
        ResponseEntity<?> response = userService.updateuser(userDTO);
        logger.info("User updated successfully with ID: {}", userDTO.getUser_id());
        return response;
    }

    @GetMapping("/showuser")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<User> getuser() {
        logger.info("Fetching all users");
        List<User> users = userService.getuser();
        logger.info("Number of users fetched: {}", users.size());
        return users;
    }

    private boolean isAdmin() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            boolean isAdmin = userDetails.getAuthorities().stream()
                    .anyMatch(grantedAuthority -> "ROLE_ADMIN".equals(grantedAuthority.getAuthority()));
            logger.info("User is admin: {}", isAdmin);
            return isAdmin;
        }
        return false;
    }
}

