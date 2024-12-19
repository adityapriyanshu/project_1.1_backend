package com.cts.main.dtos;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class MenuItemDTO {

	@NotBlank(message = "Invlalid foodName: Cannot be blank")
	@NotNull(message = "Invlalid foodName: Cannot be null")
	@Size(min=3, max=30, message="Invalid Name: Name should be 3 - 30 characters!")
	private String foodName;
	
	@NotBlank(message = "Invlalid foodPrice: Cannot be blank")
	@NotNull(message = "Invlalid foodPrice: Cannot be null")
	@NumberFormat(style = Style.NUMBER)
	private double foodPrice;
}
