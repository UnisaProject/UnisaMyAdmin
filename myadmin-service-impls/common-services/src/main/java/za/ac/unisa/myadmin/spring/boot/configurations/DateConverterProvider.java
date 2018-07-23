package za.ac.unisa.myadmin.spring.boot.configurations;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;

/**
 * Custom CXF Parameter Converter for Date
 * 
 * @author Jannie Louwrens
 *
 */
public class DateConverterProvider implements ParamConverterProvider {

	@Override
	public <T> ParamConverter<T> getConverter(Class<T> rawType, Type genericType, Annotation[] annotations) {
		if (rawType.getName().equals(Date.class.getName())) {
			return new ParamConverter<T>() {

				List<String> dateFormatStrings = Arrays.asList("MM-dd-yyyy HH:mm:ss", "yyyy-MM-dd HH:mm:ss",
						"yyyy-MM-dd'T'HH:mm:ss.SSS", "MM-dd-yyyy", "yyyy-MM-dd");

				private final DateFormat MM_DD_YYYY_HH_MM_SS = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");

				@Override
				public T fromString(String s) {
					Date ret;
					try {
						ret = getFormatedDate(s);
					} catch (ParseException ex) {
						throw new IllegalArgumentException("date does not match any of the standared formats=" + s);
					}
					return rawType.cast(ret);
				}

				@Override
				public String toString(T value) {
					return MM_DD_YYYY_HH_MM_SS.format(value);
				}

				Date getFormatedDate(String dateString) throws ParseException {
					for (String formatString : dateFormatStrings) {
						try {
							DateFormat df = new SimpleDateFormat(formatString);
							df.setLenient(false);
							return df.parse(dateString);
						} catch (ParseException ex) {
							/*
							 * This method processes multiple date formats. If ParseException occurred while
							 * processing a date string with any date format the method will have to next
							 * date format. If the program doesn't match any of the date formats this method
							 * will throw a ParseException.
							 */
						}
					}
					throw new ParseException("date is not in any of the standared formats=", 0);
				}
			};
		}
		return null;
	}

}
