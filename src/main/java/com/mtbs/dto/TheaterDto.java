/**
 * 
 */
package com.mtbs.dto;

import com.mtbs.enums.TheaterType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class TheaterDto {

	private long id;

	private String name;

	private TheaterType type;

	private String city;

	private String address;

	private List<ShowDto> shows;
}