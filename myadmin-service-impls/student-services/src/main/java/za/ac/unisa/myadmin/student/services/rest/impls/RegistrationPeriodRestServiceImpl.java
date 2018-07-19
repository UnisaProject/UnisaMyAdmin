package za.ac.unisa.myadmin.student.services.rest.impls;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.core.UriInfo;

import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.registration.services.dto.RegistrationPeriodInfo;
import za.ac.unisa.myadmin.registration.services.rest.RegistrationPeriodRestService;
import za.ac.unisa.myadmin.services.base.decorators.RegistrationPeriodServiceDecorator;
import za.ac.unisa.myadmin.services.utilities.RestServiceUtility;

public class RegistrationPeriodRestServiceImpl extends RegistrationPeriodServiceDecorator
		implements RegistrationPeriodRestService {

	@Override
	public List<RegistrationPeriodInfo> searchForRegistrationPeriods(Integer year, Integer semester, String type,
			Date date, UriInfo uriInfo)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		Set<String> allowedParameters = new HashSet<>();

		allowedParameters.clear();
		allowedParameters.add("year");
		allowedParameters.add("semester");
		allowedParameters.add("type");
		allowedParameters.add("date");
		if (RestServiceUtility.validateParameters(allowedParameters, uriInfo)) {
			return getNextDecorator().getEffectiveRegistrationPeriodsByYearAndSemesterAndTypeOnDate(year,
					semester, type, date);
		}

		allowedParameters.clear();
		allowedParameters.add("year");
		allowedParameters.add("type");
		allowedParameters.add("date");
		if (RestServiceUtility.validateParameters(allowedParameters, uriInfo)) {
			return getNextDecorator().getEffectiveRegistrationPeriodsByYearAndTypeOnDate(year, type, date);
		}

		allowedParameters.clear();
		allowedParameters.add("year");
		allowedParameters.add("semester");
		allowedParameters.add("date");
		if (RestServiceUtility.validateParameters(allowedParameters, uriInfo)) {
			return getNextDecorator().getEffectiveRegistrationPeriodsByYearAndSemesterOnDate(year, semester,
					date);
		}

		allowedParameters.clear();
		allowedParameters.add("semester");
		allowedParameters.add("type");
		allowedParameters.add("date");
		if (RestServiceUtility.validateParameters(allowedParameters, uriInfo)) {
			return getNextDecorator().getEffectiveRegistrationPeriodsBySemesterAndTypeOnDate(semester, type,
					date);
		}

		allowedParameters.clear();
		allowedParameters.add("year");
		allowedParameters.add("date");
		if (RestServiceUtility.validateParameters(allowedParameters, uriInfo)) {
			return getNextDecorator().getEffectiveRegistrationPeriodsByYearOnDate(year, date);
		}

		allowedParameters.clear();
		allowedParameters.add("semester");
		allowedParameters.add("date");
		if (RestServiceUtility.validateParameters(allowedParameters, uriInfo)) {
			return getNextDecorator().getEffectiveRegistrationPeriodsBySemesterOnDate(semester, date);
		}

		allowedParameters.clear();
		allowedParameters.add("type");
		allowedParameters.add("date");
		if (RestServiceUtility.validateParameters(allowedParameters, uriInfo)) {
			return getNextDecorator().getEffectiveRegistrationPeriodsByTypeOnDate(type, date);
		}

		allowedParameters.clear();
		allowedParameters.add("year");
		allowedParameters.add("semester");
		allowedParameters.add("type");
		if (RestServiceUtility.validateParameters(allowedParameters, uriInfo)) {
			return getNextDecorator().getRegistrationPeriodsByYearAndSemesterAndType(year, semester, type);
		}

		allowedParameters.clear();
		allowedParameters.add("year");
		allowedParameters.add("semester");
		if (RestServiceUtility.validateParameters(allowedParameters, uriInfo)) {
			return getNextDecorator().getRegistrationPeriodsByYearAndSemester(year, semester);
		}

		allowedParameters.clear();
		allowedParameters.add("year");
		allowedParameters.add("type");
		if (RestServiceUtility.validateParameters(allowedParameters, uriInfo)) {
			return getNextDecorator().getRegistrationPeriodsByYearAndType(year, type);
		}

		allowedParameters.clear();
		allowedParameters.add("semester");
		allowedParameters.add("type");
		if (RestServiceUtility.validateParameters(allowedParameters, uriInfo)) {
			return getNextDecorator().getRegistrationPeriodsBySemesterAndType(semester, type);
		}

		allowedParameters.clear();
		allowedParameters.add("year");
		if (RestServiceUtility.validateParameters(allowedParameters, uriInfo)) {
			return getNextDecorator().getRegistrationPeriodsByYear(year);
		}

		allowedParameters.clear();
		allowedParameters.add("semester");
		if (RestServiceUtility.validateParameters(allowedParameters, uriInfo)) {
			return getNextDecorator().getRegistrationPeriodsBySemester(semester);
		}

		allowedParameters.clear();
		allowedParameters.add("type");
		if (RestServiceUtility.validateParameters(allowedParameters, uriInfo)) {
			return getNextDecorator().getRegistrationPeriodsByType(type);
		}

		throw new MissingParameterException();
	}

}
