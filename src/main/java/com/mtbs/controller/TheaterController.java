/**
 * 
 */
package com.mtbs.controller;

import com.mtbs.dto.TheaterDto;
import com.mtbs.service.TheaterService;
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
@RequestMapping("theater")
public class TheaterController {

	@Autowired
	private TheaterService theaterService;

	@PostMapping("add")
	public ResponseEntity<TheaterDto> addUser(@RequestBody TheaterDto theaterDto) {

		log.info("Received Request to add new theater: " + theaterDto);

		return ResponseEntity.ok(theaterService.addTheater(theaterDto));
	}

	@GetMapping("{id}")
	public ResponseEntity<TheaterDto> getUser(@PathVariable(name = "id") @Min(value = 1, message = "Theater Id Cannot be -ve") long id) {

		log.info("Received Request to get theater: " + id);

		return ResponseEntity.ok(theaterService.getTheater(id));
	}
}