/**
 * 
 */
package com.mtbs.service;

import com.mtbs.dto.TheaterDto;


public interface TheaterService {

	TheaterDto addTheater(TheaterDto theaterDto);

	TheaterDto getTheater(long id);
}