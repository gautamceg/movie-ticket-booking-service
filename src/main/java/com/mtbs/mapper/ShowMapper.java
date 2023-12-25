
package com.mtbs.mapper;

import com.mtbs.dto.ShowDto;
import com.mtbs.dao.entity.ShowEntity;
import lombok.experimental.UtilityClass;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@UtilityClass
public class ShowMapper {

	public static List<ShowDto> toDto(List<ShowEntity> showEntities) {

		if (CollectionUtils.isNotEmpty(showEntities)) {
			return showEntities.stream().map(ShowMapper::toDto).collect(Collectors.toList());
		}

		return new ArrayList<>();
	}

	public static ShowDto toDto(ShowEntity showEntity) {

		return ShowDto.builder()
				.id(showEntity.getId())
				.showDate(showEntity.getShowDate())
				.showTime(showEntity.getShowTime())
				.rateMultiplier(showEntity.getRateMultiplier())
				.movie(MovieMapper.toDto(showEntity.getMovie()))
				.theatre(TheaterMapper.toDto(showEntity.getTheater()))
				.seats(ShowSeatsMapper.toDto(showEntity.getSeats()))
				.createdAt(showEntity.getCreatedAt())
				.updatedAt(showEntity.getUpdatedAt())
				.build();

	}

	public static ShowEntity toEntity(ShowDto showDto) {

		return ShowEntity.builder()
				.showDate(showDto.getShowDate())
				.showTime(showDto.getShowTime())
				.rateMultiplier(showDto.getRateMultiplier())
				.build();

	}

}