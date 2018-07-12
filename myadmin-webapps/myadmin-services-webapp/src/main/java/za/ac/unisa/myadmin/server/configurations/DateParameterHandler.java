package za.ac.unisa.myadmin.server.configurations;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.ws.rs.ext.ParamConverter;

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
