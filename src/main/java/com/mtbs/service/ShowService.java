/**
 * 
 */
package com.mtbs.service;

import com.mtbs.dto.PageResponse;
import com.mtbs.dto.ShowDto;

import java.time.LocalDate;
import java.time.LocalTime;


public interface ShowService {

	ShowDto addShow(ShowDto showDto);

	ShowDto updateShow(ShowDto showDto, long showId);

	void deleteShow(long showId);

	PageResponse<ShowDto> searchShows(String movieName, String city, LocalDate showDate, LocalTime showTime, int pageNo, int limit);

}