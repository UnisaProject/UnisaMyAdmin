package za.ac.unisa.myadmin.spring.boot.configurations;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.ws.rs.ext.ParamConverter;

/**
 * This is a delegate responsible for converting between a String representation
 * of a date parameter value and the corresponding custom Java Date type.
 * 
 * The format of the date string is expected to be yyyy-MM-dd, e.g. 2018-07-28
 * 
 * @author Jannie Louwrens
 *
 */
public class DateParameterHandler implements ParamConverter<Date> {

	private static final DateFormat YYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");

	static {
		YYYY_MM_DD.setTimeZone(TimeZone.getTimeZone("GMT"));
	}

	@Override
	public Date fromString(String s) {
		Date ret;
		try {
			ret = YYYY_MM_DD.parse(s);
		} catch (ParseException ex) {
			throw new IllegalArgumentException("date is not in YYYY-MM-DD format=" + s);
		}
		return ret;
	}

	@Override
	public String toString(Date value) {
		return YYYY_MM_DD.format(value);
	}

}
