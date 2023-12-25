
package com.mtbs.mapper;

import com.mtbs.dto.UserDto;
import com.mtbs.dao.entity.UserEntity;
import lombok.experimental.UtilityClass;


@UtilityClass
public class UserMapper {

	public static UserEntity toEntity(UserDto userDto) {

		return UserEntity.builder()
				.name(userDto.getName())
				.mobile(userDto.getMobile())
				.build();

	}

	public static UserDto toDto(UserEntity userEntity) {

		return UserDto.builder()
				.id(userEntity.getId())
				.name(userEntity.getName())
				.mobile(userEntity.getMobile())
				.build();
	}

}