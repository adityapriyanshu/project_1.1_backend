package com.cts.main.dtos;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MenuItemDTO {

	@NotBlank(message = "Invlalid foodName: Cannot be blank")
	@NotNull(message = "Invlalid foodName: Cannot be null")
	@Size(min = 3, max = 30, message = "Invalid Name: Name should be 3 - 30 characters!")
	private String foodName;

	@NotNull(message = "Invalid foodPrice: Cannot be null")
	@Min(value = 1, message = "Please select a value greater than or equal to 1 for food price!")
	@Max(value = 100, message = "Please select a value less than or equal to 100 for food price!")
	private double foodPrice;
}
