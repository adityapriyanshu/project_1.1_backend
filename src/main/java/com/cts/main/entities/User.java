package com.cts.main.entities;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="User_Details")
@Data
@NoArgsConstructor
//@AllArgsConstructor
//@Builder
public class User{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long user_id;
	String username;
	String password;
	String roles;
	
	public User(String username, String password, String roles) {
		super();
		this.username = username;
		this.password = password;
		this.roles = roles;
	}
	
	
}
