package com.practice.demo.user.signup.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.demo.user.signup.model.CreateUserRequestModel;
import com.practice.demo.user.signup.service.UserService;
import com.practice.demo.user.signup.shared.UserDto;

@RestController
@RequestMapping("/signup")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping
	public String createUser(@Valid @RequestBody CreateUserRequestModel userDetails) {
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		UserDto userDto = modelMapper.map(userDetails, UserDto.class);
		userService.createUser(userDto);
		
		return "created";
	}
	
}
