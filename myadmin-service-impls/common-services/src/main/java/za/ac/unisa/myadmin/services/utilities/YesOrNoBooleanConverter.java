package za.ac.unisa.myadmin.services.utilities;

import javax.persistence.AttributeConverter;

/**
 * A class that implements AttributeConverter to convert an entity boolean
 * attribute value into the database column CHAR representation and back again
 * 
 * @author Jannie Louwrens
 *
 */
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
