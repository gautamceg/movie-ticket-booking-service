
package com.mtbs.adapter;

import com.mtbs.dto.MovieDto;
import com.mtbs.model.MovieEntity;
import lombok.experimental.UtilityClass;


@UtilityClass
public class MovieAdapter {

	public static MovieEntity toEntity(MovieDto movieDto) {

		return MovieEntity.builder()
				.name(movieDto.getName())
				.language(movieDto.getLanguage())
				.certificateType(movieDto.getCertificateType())
				.releaseDate(movieDto.getReleaseDate())
				.build();

	}

	public static MovieDto toDto(MovieEntity movieEntity) {

		return MovieDto.builder()
				.id(movieEntity.getId())
				.name(movieEntity.getName())
				.language(movieEntity.getLanguage())
				.certificateType(movieEntity.getCertificateType())
				.releaseDate(movieEntity.getReleaseDate())
				.build();
	}

}