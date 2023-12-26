/**
 * 
 */
package com.mtbs.controller;

import com.mtbs.dto.MovieDto;
import com.mtbs.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.constraints.Min;

@Log4j2
@RestController
@RequestMapping("movie")
public class MovieController {

	@Autowired
	private MovieService movieService;

	@Operation(summary = "Add movie")
	@PostMapping
	public ResponseEntity<MovieDto> addMovie(@RequestBody MovieDto movieDto) {

		log.info("Received Request to add new movie: " + movieDto);

		return ResponseEntity.ok(movieService.addMovie(movieDto));
	}

	@Operation(summary = "Get movie by id")
	@GetMapping("{id}")
	public ResponseEntity<MovieDto> getUser(@PathVariable(name = "id") @Min(value = 1, message = "Movie Id Cannot be -ve") long id) {

		log.info("Received Request to get movie: " + id);

		return ResponseEntity.ok(movieService.getMovie(id));
	}
}