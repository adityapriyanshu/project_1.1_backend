package com.cts.main.dtos;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserDTO {
	
	private Long user_id;

    @Pattern(regexp = "^[A-Z][a-zA-Z]{2,11}$", message = "Username should be 3 to 12 characters long and starting with a capital letter.")
    private String username;

    @Pattern(regexp = "^(?=.*[!@#$%^&*]).{4,10}$", message = "Password should be 4 to 10 characters long with at least 1 special character.")
    private String password;

    @Pattern(regexp = "ROLE_ADMIN|ROLE_CUSTOMER", message = "Role should be either ROLE_ADMIN or ROLE_CUSTOMER.")
    private String roles;
}
