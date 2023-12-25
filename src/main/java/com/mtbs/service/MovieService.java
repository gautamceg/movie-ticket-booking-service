/**
 * 
 */
package com.mtbs.service;

import com.mtbs.dto.MovieDto;


public interface MovieService {

	MovieDto addMovie(MovieDto movieDto);

	MovieDto getMovie(long id);
}