/**
 * 
 */
package com.mtbs.service;

import com.mtbs.dto.TheaterDto;

/**
 * @author naveen
 *
 * @date 05-Sep-2019
 */
public interface TheaterService {

	TheaterDto addTheater(TheaterDto theaterDto);

	TheaterDto getTheater(long id);
}