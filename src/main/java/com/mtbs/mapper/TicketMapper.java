
package com.mtbs.mapper;

import com.mtbs.dto.TicketDto;
import com.mtbs.dao.entity.TicketEntity;
import lombok.experimental.UtilityClass;


@UtilityClass
public class TicketMapper {

	public static TicketEntity toEntity(TicketDto ticketDto) {

		return TicketEntity.builder()
				.allottedSeats(ticketDto.getAllottedSeats())
				.amount(ticketDto.getAmount())
				.build();

	}

	public static TicketDto toDto(TicketEntity ticketEntity) {

		return TicketDto.builder()
				.id(ticketEntity.getId())
				.allottedSeats(ticketEntity.getAllottedSeats())
				.amount(ticketEntity.getAmount())
				.show(ShowMapper.toDto(ticketEntity.getShow()))
				.build();
	}

}