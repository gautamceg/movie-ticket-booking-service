/**
 * 
 */
package com.mtbs.service;

import com.mtbs.dto.UserDto;

/**
 * @author naveen
 *
 * @date 05-Sep-2019
 */
public interface UserService {

	UserDto addUser(UserDto userDto);

	UserDto getUser(long id);
}