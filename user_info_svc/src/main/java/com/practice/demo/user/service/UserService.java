package com.practice.demo.user.service;

import org.springframework.stereotype.Service;

import com.practice.demo.user.shared.UserDto;

@Service
public interface UserService {
	UserDto createUser(UserDto UserDetails);
}
