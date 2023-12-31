/**
 * 
 */
package com.mtbs.controller;

import com.mtbs.dto.BookTicketRequestDto;
import com.mtbs.dto.TicketDto;
import com.mtbs.service.TicketService;
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
@RequestMapping("ticket")
public class TicketController {

	@Autowired
	private TicketService ticketService;

	@Operation(summary = "Book ticket")
	@PostMapping
	public ResponseEntity<TicketDto> bookTicket(@RequestBody BookTicketRequestDto bookTicketRequestDto) {

		log.info("Received Request to book ticket: " + bookTicketRequestDto);

		return ResponseEntity.ok(ticketService.bookTicket(bookTicketRequestDto));
	}

	@Operation(summary = "Get ticket by id")
	@GetMapping("{id}")
	public ResponseEntity<TicketDto> getTicket(@PathVariable(name = "id") @Min(value = 1, message = "Ticket Id Cannot be -ve") long id) {

		log.info("Received Request to get ticket: " + id);

		return ResponseEntity.ok(ticketService.getTicket(id));
	}
}