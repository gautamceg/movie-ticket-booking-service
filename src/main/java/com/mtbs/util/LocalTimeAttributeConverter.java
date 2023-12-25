/**
 * 
 */
package com.mtbs.util;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.time.LocalTime;
import java.util.Date;


@Converter(autoApply = true)
public class LocalTimeAttributeConverter implements AttributeConverter<LocalTime, Date> {

	@Override
	public Date convertToDatabaseColumn(LocalTime attribute) {
		return DateUtils.convertLocalTimeToDate(attribute);
	}

	@Override
	public LocalTime convertToEntityAttribute(Date dbData) {
		return DateUtils.getLocalTimeFromDate(dbData);
	}

}