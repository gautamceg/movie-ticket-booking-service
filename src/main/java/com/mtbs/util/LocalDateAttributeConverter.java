/**
 * 
 */
package com.mtbs.util;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.time.LocalDate;
import java.util.Date;


@Converter(autoApply = true)
public class LocalDateAttributeConverter implements AttributeConverter<LocalDate, Date> {

	@Override
	public Date convertToDatabaseColumn(LocalDate attribute) {
		return DateUtils.convertLocalDateToDate(attribute);
	}

	@Override
	public LocalDate convertToEntityAttribute(Date dbData) {
		return DateUtils.getLocalDateFromDate(dbData);
	}

}