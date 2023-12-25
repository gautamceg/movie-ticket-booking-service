/**
 * 
 */
package com.mtbs.service;

import com.mtbs.dto.MovieDto;

/**
 * @author naveen
 *
 * @date 05-Sep-2019
 */
public interface MovieService {

	MovieDto addMovie(MovieDto movieDto);

	MovieDto getMovie(long id);
}