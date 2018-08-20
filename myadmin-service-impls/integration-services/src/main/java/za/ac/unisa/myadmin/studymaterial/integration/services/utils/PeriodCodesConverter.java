package za.ac.unisa.myadmin.studymaterial.integration.services.utils;

import java.util.Objects;

public class PeriodCodesConverter {

	public static String convert(String code){
		if(Objects.isNull(code)){
			return null;
		}
		if ("1".equalsIgnoreCase(code)){
			return "January/February";
		}
		if("6".equalsIgnoreCase(code)){
			return "May/June";
		}
		if("10".equalsIgnoreCase(code)){
			return "October/November";
		}

		// Else we don't have a proper description
		return code;
	}
}
