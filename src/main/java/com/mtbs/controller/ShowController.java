/**
 * 
 */
package com.mtbs.controller;

import com.mtbs.dto.PageResponse;
import com.mtbs.dto.ShowDto;
import com.mtbs.service.ShowService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.constraints.Min;
import java.time.LocalDate;
import java.time.LocalTime;


@Log4j2
@RestController
@RequestMapping("show")
public class ShowController {

	@Autowired
	private ShowService showService;

	@Operation(summary = "Search show")
	@GetMapping("/search/{pageNo}/{limit}")
	public ResponseEntity<PageResponse<ShowDto>> search(
			@PathVariable(name = "pageNo") @Min(value = 1, message = "Page No Cannot be less than 1") int pageNo,
			@PathVariable(name = "limit") @Min(value = 1, message = "Limit Cannot be less than 1") int limit,
			@RequestParam(name = "movieName", required = false) String movieName,
			@RequestParam(name = "city", required = false) String city,
			@RequestParam(name = "showDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate showDate,
			@RequestParam(name = "showTime", required = false) @DateTimeFormat(pattern = "HH:mm:ss") LocalTime showTime) {

		log.info("Received Request to search shows for Page: [Number: " + pageNo + ", Limit: " + limit + "]");

		return ResponseEntity.ok(showService.searchShows(movieName, city, showDate, showTime, pageNo, limit));
	}

	@Operation(summary = "Add show")
	@PostMapping
	public ResponseEntity<ShowDto> addShow(@RequestBody ShowDto showDto) {

		log.info("Received Request to add new show: " + showDto);

		return ResponseEntity.ok(showService.addShow(showDto));
	}

	@Operation(summary = "Update show")
	@PutMapping("/{showId}")
	public ResponseEntity<ShowDto> updateShow(@RequestBody ShowDto showDto, @PathVariable long showId) {

		log.info("Received Request to update existing show: " + showDto);

		return ResponseEntity.ok(showDto);
	}

	@Operation(summary = "Delete show")
	@DeleteMapping("/{showId}")
	public ResponseEntity<ShowDto> deleteShow(@RequestBody ShowDto showDto, @PathVariable long showId) {

		log.info("Received Request to delete existing show: " + showDto);
		showService.deleteShow(showId);

		return ResponseEntity.ok(showDto);
	}

}