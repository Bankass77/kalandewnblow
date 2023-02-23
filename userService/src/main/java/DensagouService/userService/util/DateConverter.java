package DensagouService.userService.util;

import java.sql.Date;
import java.time.LocalDate;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class DateConverter implements AttributeConverter<LocalDate, Date> {

	@Override
	public Date convertToDatabaseColumn(LocalDate localDate) {
		return localDate != null ? Date.valueOf(localDate) : null;
	}

	@Override
	public LocalDate convertToEntityAttribute(Date date) {
		return date != null ? date.toLocalDate() : null;
	}
}