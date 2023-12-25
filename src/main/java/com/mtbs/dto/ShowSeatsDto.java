/**
 * 
 */
package com.mtbs.dto;

import com.mtbs.enums.SeatType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class ShowSeatsDto {

	private long id;

	private String seatNumber;

	private int rate;

	private SeatType seatType;

	private boolean booked;

	private Date bookedAt;

}