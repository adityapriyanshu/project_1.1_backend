package com.cts.main.config;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.cts.main.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cts.main.repository.UserRepository;

//import com.cts.main.repository.UserRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
    private UserRepository userRepository;

//    public CustomUserDetailsService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(usernameOrEmail);

      return user.map(CustomUserDetails::new)
    		  .orElseThrow(()-> new UsernameNotFoundException(usernameOrEmail+"User not Found"));
    }
}
