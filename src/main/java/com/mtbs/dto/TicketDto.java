/**
 * 
 */
package com.mtbs.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author naveen
 *
 * @date 05-Sep-2019
 */
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class TicketDto {

	private long id;

	private String allottedSeats;

	private double amount;

	private Date bookedAt;

	private ShowDto show;
}