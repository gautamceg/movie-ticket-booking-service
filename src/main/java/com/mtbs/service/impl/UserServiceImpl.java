/**
 * 
 */
package com.mtbs.service.impl;

import com.mtbs.mapper.UserMapper;
import com.mtbs.dto.UserDto;
import com.mtbs.exception.DuplicateRecordException;
import com.mtbs.dao.entity.UserEntity;
import com.mtbs.dao.repository.UserRepository;
import com.mtbs.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;


@Log4j2
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDto addUser(UserDto userDto) {

		if (userRepository.existsByMobile(userDto.getMobile())) {
			throw new DuplicateRecordException("User Already Exists with Mobile: " + userDto.getMobile());
		}

		log.info("Adding New User: " + userDto);

		UserEntity userEntity = UserMapper.toEntity(userDto);

		userEntity = userRepository.save(userEntity);

		log.info("Added New User [id: " + userEntity.getId() + ", Mobile: " + userEntity.getMobile() + "]");

		return UserMapper.toDto(userEntity);
	}

	@Override
	public UserDto getUser(long id) {

		log.info("Searching User by id: " + id);

		Optional<UserEntity> userEntity = userRepository.findById(id);

		if (!userEntity.isPresent()) {
			log.error("User not found for id: " + id);
			throw new EntityNotFoundException("User Not Found with ID: " + id);
		}

		return UserMapper.toDto(userEntity.get());
	}

}