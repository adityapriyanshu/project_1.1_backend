package com.cts.main.dtos;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerOrderDTO {

	@NotBlank(message = "Invalid Name: Cannot be blank!")
	@NotNull(message = "Invalid Name: Cannot be null!")
	@Size(min = 3, max = 30, message = "Invalid Name: Name should be 3 - 30 characters!")
	private String customerName;

	@NotBlank(message = "Invalid Phone number: Empty number!")
	@NotNull(message = "Invalid Phone number: Number is NULL!")
	@Pattern(regexp = "^\\d{10}$", message = "Invalid phone number!")
	private String customerPhoneNumber;

	@NotBlank(message = "Invalid Table Number: Cannot be blank!")
	@NotNull(message = "Invalid Table Number: Cannot be null!")
	@Size(min = 1, max = 10, message = "Please select between 1-10 for table number!")
	private String customerTableNumber;

	@NotNull(message = "Order Items Required!")
	@Size(min = 1, message = "Please select at least one Food Item to order!")
	private List<Long> orderItems;
	
}
