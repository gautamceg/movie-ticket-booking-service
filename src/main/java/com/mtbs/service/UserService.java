/**
 * 
 */
package com.mtbs.service;

import com.mtbs.dto.UserDto;


public interface UserService {

	UserDto addUser(UserDto userDto);

	UserDto getUser(long id);
}