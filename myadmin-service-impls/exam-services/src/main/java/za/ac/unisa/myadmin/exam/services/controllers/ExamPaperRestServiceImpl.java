package za.ac.unisa.myadmin.exam.services.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.core.UriInfo;

import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.exam.rest.services.ExamPaperRestService;
import za.ac.unisa.myadmin.exam.services.dto.ExamPaperInfo;
import za.ac.unisa.myadmin.service.base.decorators.ExamPaperServiceDecorator;
import za.ac.unisa.myadmin.service.rest.impl.utils.RestImplUtils;

public class ExamPaperRestServiceImpl extends ExamPaperServiceDecorator implements ExamPaperRestService {

	@Override
	public List<ExamPaperInfo> searchForExamPeriodDates(Integer year, List<String> courseCodes, UriInfo uriInfo)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		Set<String> allowedParameters = new HashSet<String>();

		allowedParameters.clear();
		allowedParameters.add("year");
		allowedParameters.add("courseCode");
		if (RestImplUtils.validateParameters(allowedParameters, uriInfo)) {
			return getNextDecorator().getExamPapersByYearAndCourseCodes(year, courseCodes);
		}

		allowedParameters.clear();
		allowedParameters.add("year");
		if (RestImplUtils.validateParameters(allowedParameters, uriInfo)) {
			return getNextDecorator().getExamPapersByYear(year);
		}

		allowedParameters.clear();
		allowedParameters.add("courseCode");
		if (RestImplUtils.validateParameters(allowedParameters, uriInfo)) {
			return getNextDecorator().getExamPapersByCourseCodes(courseCodes);
		}

		throw new MissingParameterException();
	}

}
