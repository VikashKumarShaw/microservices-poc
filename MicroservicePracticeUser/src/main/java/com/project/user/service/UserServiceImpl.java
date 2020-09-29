package com.project.user.service;

import java.util.UUID;

import com.project.user.data.UserEntity;
import com.project.user.shared.UserDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

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
