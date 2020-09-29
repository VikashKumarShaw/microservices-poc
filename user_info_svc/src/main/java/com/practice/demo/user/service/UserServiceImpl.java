package com.practice.demo.user.service;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import com.practice.demo.user.data.UserEntity;
import com.practice.demo.user.shared.UserDto;

public class UserServiceImpl implements UserService {

	@Override
	public UserDto createUser(UserDto userDetails) {
		// TODO Auto-generated method stub
		userDetails.setUserId(UUID.randomUUID().toString());
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserEntity  userEntity =  modelMapper.map(userDetails, UserEntity.class);
		
		userEntity.setEncryptedPassword("test");

		return null;
	}

}
