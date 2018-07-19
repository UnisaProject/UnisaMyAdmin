package za.ac.unisa.myadmin.exam.services.rest.impls;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.core.UriInfo;

import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.exam.services.dto.ExamPaperInfo;
import za.ac.unisa.myadmin.exam.services.rest.ExamPaperRestService;
import za.ac.unisa.myadmin.services.base.decorators.ExamPaperServiceDecorator;
import za.ac.unisa.myadmin.services.utilities.RestServiceUtility;

public class ExamPaperRestServiceImpl extends ExamPaperServiceDecorator implements ExamPaperRestService {

	@Override
	public List<ExamPaperInfo> searchForExamPeriodDates(Integer year, List<String> courseCodes, UriInfo uriInfo)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		Set<String> allowedParameters = new HashSet<String>();

		allowedParameters.clear();
		allowedParameters.add("year");
		allowedParameters.add("courseCode");
		if (RestServiceUtility.validateParameters(allowedParameters, uriInfo)) {
			return getNextDecorator().getExamPapersByYearAndCourseCodes(year, courseCodes);
		}

		allowedParameters.clear();
		allowedParameters.add("year");
		if (RestServiceUtility.validateParameters(allowedParameters, uriInfo)) {
			return getNextDecorator().getExamPapersByYear(year);
		}

		allowedParameters.clear();
		allowedParameters.add("courseCode");
		if (RestServiceUtility.validateParameters(allowedParameters, uriInfo)) {
			return getNextDecorator().getExamPapersByCourseCodes(courseCodes);
		}

		throw new MissingParameterException();
	}

}
