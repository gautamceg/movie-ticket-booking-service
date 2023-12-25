
package com.mtbs.mapper;

import com.mtbs.dto.ShowSeatsDto;
import com.mtbs.dao.entity.ShowSeatsEntity;
import lombok.experimental.UtilityClass;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@UtilityClass
public class ShowSeatsMapper {

	public static List<ShowSeatsDto> toDto(List<ShowSeatsEntity> seatsEntities) {

		if (CollectionUtils.isNotEmpty(seatsEntities)) {
			return seatsEntities.stream().map(ShowSeatsMapper::toDto).collect(Collectors.toList());
		}

		return new ArrayList<>();
	}

	public static ShowSeatsDto toDto(ShowSeatsEntity seatsEntity) {

		return ShowSeatsDto.builder()
				.id(seatsEntity.getId())
				.seatNumber(seatsEntity.getSeatNumber())
				.rate(seatsEntity.getRate())
				.seatType(seatsEntity.getSeatType())
				.booked(seatsEntity.isBooked())
				.bookedAt(seatsEntity.getBookedAt())
				.build();

	}

}