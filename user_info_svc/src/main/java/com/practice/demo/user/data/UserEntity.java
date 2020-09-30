package com.practice.demo.user.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "User")
@Data
public class UserEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -698341412719409754L;
	
	@Id
	private long id;
	
	@Column(nullable = false, length =  50)
	private String firstName;
	
	@Column(nullable = false, length =  50)
	private String lastName;
	
	@Column(nullable = false, length =  120, unique = true)
	private String email;
	
	@Column(nullable = false, unique = true)
	private String userId;
	
	@Column(nullable = false, unique = true)
	private String encryptedPassword;
	
}
