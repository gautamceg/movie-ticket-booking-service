
package com.mtbs.adapter;

import com.mtbs.dto.ShowDto;
import com.mtbs.model.ShowEntity;
import lombok.experimental.UtilityClass;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@UtilityClass
public class ShowAdapter {

	public static List<ShowDto> toDto(List<ShowEntity> showEntities) {

		if (CollectionUtils.isNotEmpty(showEntities)) {
			return showEntities.stream().map(ShowAdapter::toDto).collect(Collectors.toList());
		}

		return new ArrayList<>();
	}

	public static ShowDto toDto(ShowEntity showEntity) {

		return ShowDto.builder()
				.id(showEntity.getId())
				.showDate(showEntity.getShowDate())
				.showTime(showEntity.getShowTime())
				.rateMultiplier(showEntity.getRateMultiplier())
				.movie(MovieAdapter.toDto(showEntity.getMovie()))
				.theatre(TheaterAdapter.toDto(showEntity.getTheater()))
				.seats(ShowSeatsAdapter.toDto(showEntity.getSeats()))
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