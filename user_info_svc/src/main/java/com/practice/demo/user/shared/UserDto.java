package com.practice.demo.user.shared;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserDto implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4661850029912441509L;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String userId;
	private String encryptedPassword;
	
		
}
