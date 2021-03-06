package za.ac.unisa.myadmin.exam.services.rest.impls;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.core.UriInfo;

import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.exam.services.dto.ExaminationInfo;
import za.ac.unisa.myadmin.exam.services.rest.ExaminationRestService;
import za.ac.unisa.myadmin.services.base.decorators.ExaminationServiceDecorator;
import za.ac.unisa.myadmin.services.utilities.RestServiceUtility;

public class ExaminationRestServiceImpl extends ExaminationServiceDecorator implements ExaminationRestService {

	@Override
	public List<ExaminationInfo> searchForExaminations(Integer year, Integer examPeriodCode, List<String> courseCodes,
			UriInfo uriInfo) throws MissingParameterException, InvalidParameterException, OperationFailedException {
		Set<String> allowedParameters = new HashSet<>();

		allowedParameters.clear();
		allowedParameters.add("year");
		allowedParameters.add("examPeriodCode");
		allowedParameters.add("courseCode");
		if (RestServiceUtility.validateParameters(allowedParameters, uriInfo)) {
			return getNextDecorator().getExaminationsByYearAndExamPeriodCodeAndCourseCodes(year, examPeriodCode,
					courseCodes);
		}

		allowedParameters.clear();
		allowedParameters.add("examPeriodCode");
		allowedParameters.add("courseCode");
		if (RestServiceUtility.validateParameters(allowedParameters, uriInfo)) {
			return getNextDecorator().getExaminationsByExamPeriodCodeAndCourseCodes(examPeriodCode, courseCodes);
		}

		allowedParameters.clear();
		allowedParameters.add("year");
		allowedParameters.add("courseCode");
		if (RestServiceUtility.validateParameters(allowedParameters, uriInfo)) {
			return getNextDecorator().getExaminationsByYearAndCourseCodes(year, courseCodes);
		}

		allowedParameters.clear();
		allowedParameters.add("year");
		allowedParameters.add("examPeriodCode");
		if (RestServiceUtility.validateParameters(allowedParameters, uriInfo)) {
			return getNextDecorator().getExaminationsByYearAndExamPeriodCode(year, examPeriodCode);
		}

		allowedParameters.clear();
		allowedParameters.add("year");
		if (RestServiceUtility.validateParameters(allowedParameters, uriInfo)) {
			return getNextDecorator().getExaminationsByYear(year);
		}

		allowedParameters.clear();
		allowedParameters.add("examPeriodCode");
		if (RestServiceUtility.validateParameters(allowedParameters, uriInfo)) {
			return getNextDecorator().getExaminationsByExamPeriodCode(examPeriodCode);
		}

		allowedParameters.clear();
		allowedParameters.add("courseCode");
		if (RestServiceUtility.validateParameters(allowedParameters, uriInfo)) {
			return getNextDecorator().getExaminationsByCourseCodes(courseCodes);
		}

		throw new MissingParameterException();
	}

}
