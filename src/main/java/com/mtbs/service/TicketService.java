/**
 * 
 */
package com.mtbs.service;

import com.mtbs.dto.BookTicketRequestDto;
import com.mtbs.dto.TicketDto;


public interface TicketService {

	TicketDto bookTicket(BookTicketRequestDto bookTicketRequestDto);

	TicketDto getTicket(long id);
}