package com.practice.demo.user.service;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.practice.demo.user.data.UserEntity;
import com.practice.demo.user.data.UserRepository;
import com.practice.demo.user.shared.UserDto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	/*
	 * @Autowired public UserServiceImpl(UserRepository usersRepository,
	 * BCryptPasswordEncoder bCryptPasswordEncoder) {
	 * 
	 * // TODO Auto-generated constructor stub this.userRepository =
	 * usersRepository; this.bCryptPasswordEncoder = bCryptPasswordEncoder; }
	 */

	@Override
	public UserDto createUser(UserDto userDetails) {
		// TODO Auto-generated method stub
		userDetails.setUserId(UUID.randomUUID().toString());

		userDetails.setEncryptedPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		UserEntity userEntity = modelMapper.map(userDetails, UserEntity.class);

		userRepository.save(userEntity);

		UserDto returnValue = modelMapper.map(userEntity, UserDto.class);

		return returnValue;
	}

}
