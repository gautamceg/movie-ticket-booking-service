
package com.mtbs.mapper;

import com.mtbs.dto.TheaterDto;
import com.mtbs.dao.entity.TheaterEntity;
import lombok.experimental.UtilityClass;


@UtilityClass
public class TheaterMapper {

	public static TheaterEntity toEntity(TheaterDto theaterDto) {

		return TheaterEntity.builder()
				.name(theaterDto.getName())
				.type(theaterDto.getType())
				.city(theaterDto.getCity())
				.address(theaterDto.getAddress())
				.build();
	}

	public static TheaterDto toDto(TheaterEntity theaterEntity) {

		return TheaterDto.builder()
				.id(theaterEntity.getId())
				.name(theaterEntity.getName())
				.type(theaterEntity.getType())
				.city(theaterEntity.getCity())
				.address(theaterEntity.getAddress())
				.build();
	}

}