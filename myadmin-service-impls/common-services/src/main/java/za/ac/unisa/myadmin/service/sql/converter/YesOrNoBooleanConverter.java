package za.ac.unisa.myadmin.service.sql.converter;

import javax.persistence.AttributeConverter;

public class YesOrNoBooleanConverter implements AttributeConverter<Boolean, String> {

	@Override
	public String convertToDatabaseColumn(Boolean value) {
		if (Boolean.TRUE.equals(value)) {
			return "Y";
		} else {
			return "N";
		}
	}

	@Override
	public Boolean convertToEntityAttribute(String value) {
		return "Y".equals(value);
	}

}
