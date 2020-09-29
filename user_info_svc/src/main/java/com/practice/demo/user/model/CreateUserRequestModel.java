package com.practice.demo.user.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CreateUserRequestModel {

	@Pattern(regexp = "^[A-Za-z]*$", message = "Invalid Input")
	@NotNull(message = "Invalid Input - First name cannot be null")
	private String firstName;
	
	@Pattern(regexp = "^[A-Za-z]*$", message = "Invalid Input")
	@NotNull(message = "Invalid Input - Last name cannot be null")
	private String lastName;
	
	@NotNull(message = "Invalid Input - Email cannot be null")
	@Email(message = "Invalid email")
	private String email;
	
	@NotNull(message = "Invalid Input - Password cannot be null")
	@Size(min = 8, max = 16, message = "Password must be equal or greater than 8 characters and less than 16 characters ")
	private String password;

}
