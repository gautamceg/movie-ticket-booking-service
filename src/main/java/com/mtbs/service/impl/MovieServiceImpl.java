/**
 * 
 */
package com.mtbs.service.impl;

import com.mtbs.mapper.MovieMapper;
import com.mtbs.dto.MovieDto;
import com.mtbs.exception.DuplicateRecordException;
import com.mtbs.dao.entity.MovieEntity;
import com.mtbs.dao.repository.MovieRepository;
import com.mtbs.service.MovieService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;


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

		MovieEntity movieEntity = MovieMapper.toEntity(movieDto);

		movieEntity = movieRepository.save(movieEntity);

		log.info("Added New Movie [id: " + movieEntity.getId() + ", Name: " + movieEntity.getName() + ", Language: " + movieEntity.getLanguage() + "]");

		return MovieMapper.toDto(movieEntity);
	}

	@Override
	public MovieDto getMovie(long id) {
		log.info("Searching Movie by id: " + id);

		Optional<MovieEntity> movieEntity = movieRepository.findById(id);

		if (!movieEntity.isPresent()) {
			log.error("Movie not found for id: " + id);
			throw new EntityNotFoundException("Movie Not Found with ID: " + id);
		}

		return MovieMapper.toDto(movieEntity.get());
	}

}