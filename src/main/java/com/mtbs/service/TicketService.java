/**
 * 
 */
package com.mtbs.service;

import com.mtbs.dto.BookTicketRequestDto;
import com.mtbs.dto.TicketDto;

/**
 * @author naveen
 *
 * @date 05-Sep-2019
 */
public interface TicketService {

	TicketDto bookTicket(BookTicketRequestDto bookTicketRequestDto);

	TicketDto getTicket(long id);
}