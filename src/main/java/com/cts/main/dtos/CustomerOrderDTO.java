package com.cts.main.dtos;

import java.util.Map;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerOrderDTO {

	@NotBlank(message = "Invalid Name: Cannot be blank!")
	@NotNull(message = "Invalid Name: Cannot be null!")
	@Size(min = 3, max = 30, message = "Invalid Name: Name should be 3 - 30 characters!")
	private String customerName;

	@NotBlank(message = "Invalid Phone number: Empty number!")
	@NotNull(message = "Invalid Phone number: Number is NULL!")
	@Pattern(regexp = "^\\d{10}$", message = "Invalid phone number!")
	private String customerPhoneNumber;

	@NotNull(message = "Customer table number is required")
	@Pattern(regexp = "^(10|[1-9])$", message = "Choose table number between 1 and 10")
	private String customerTableNumber;

//	@NotNull(message = "Order Items Required!")
//	@Size(min = 1, message = "Please select at least one Food Item to order!")
//	private List<Long> orderItems;

	@NotNull(message = "Order items are required")
	private Map<Long, @Positive @Max(value = 5, message = "Quantity must not exceed 5") Integer> orderItems;
}
