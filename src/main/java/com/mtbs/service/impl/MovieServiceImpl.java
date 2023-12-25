/**
 * 
 */
package com.mtbs.service.impl;

import com.mtbs.adapter.MovieAdapter;
import com.mtbs.dto.MovieDto;
import com.mtbs.exception.DuplicateRecordException;
import com.mtbs.model.MovieEntity;
import com.mtbs.repository.MovieRepository;
import com.mtbs.service.MovieService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;

/**
 * @author naveen
 *
 * @date 05-Sep-2019
 */
@Log4j2
@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieRepository movieRepository;

	@Override
	public MovieDto addMovie(MovieDto movieDto) {

		if (movieRepository.existsByNameAndLanguage(movieDto.getName(), movieDto.getLanguage())) {
			throw new DuplicateRecordException("Movie Already Exists with Name: " + movieDto.getName() + " in Language: " + movieDto.getLanguage());
		}

		log.info("Adding New Movie: " + movieDto);

		MovieEntity movieEntity = MovieAdapter.toEntity(movieDto);

		movieEntity = movieRepository.save(movieEntity);

		log.info("Added New Movie [id: " + movieEntity.getId() + ", Name: " + movieEntity.getName() + ", Language: " + movieEntity.getLanguage() + "]");

		return MovieAdapter.toDto(movieEntity);
	}

	@Override
	public MovieDto getMovie(long id) {
		log.info("Searching Movie by id: " + id);

		Optional<MovieEntity> movieEntity = movieRepository.findById(id);

		if (!movieEntity.isPresent()) {
			log.error("Movie not found for id: " + id);
			throw new EntityNotFoundException("Movie Not Found with ID: " + id);
		}

		return MovieAdapter.toDto(movieEntity.get());
	}

}