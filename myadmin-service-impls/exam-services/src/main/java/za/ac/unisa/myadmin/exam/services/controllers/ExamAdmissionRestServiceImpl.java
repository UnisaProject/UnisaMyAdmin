package za.ac.unisa.myadmin.exam.services.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.core.UriInfo;

import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.exam.rest.services.ExamAdmissionRestService;
import za.ac.unisa.myadmin.exam.services.dto.ExamAdmissionInfo;
import za.ac.unisa.myadmin.service.base.decorators.ExamAdmissionServiceDecorator;
import za.ac.unisa.myadmin.service.rest.impl.utils.RestImplUtils;

public class ExamAdmissionRestServiceImpl extends ExamAdmissionServiceDecorator implements ExamAdmissionRestService {

	@Override
	public List<ExamAdmissionInfo> searchForExamAdmissions(Integer year, Integer examPeriodCode, Integer examType,
			UriInfo uriInfo) throws MissingParameterException, InvalidParameterException, OperationFailedException {
		Set<String> allowedParameters = new HashSet<String>();

		allowedParameters.clear();
		allowedParameters.add("year");
		allowedParameters.add("examPeriodCode");
		allowedParameters.add("examType");
		if (RestImplUtils.validateParameters(allowedParameters, uriInfo)) {
			return getNextDecorator().getExamAdmissionsByYearAndExamPeriodCodeAndExamType(year, examPeriodCode,
					examType);
		}

		allowedParameters.clear();
		allowedParameters.add("examPeriodCode");
		allowedParameters.add("examType");
		if (RestImplUtils.validateParameters(allowedParameters, uriInfo)) {
			return getNextDecorator().getExamAdmissionsByExamPeriodCodeAndExamType(examPeriodCode, examType);
		}

		allowedParameters.clear();
		allowedParameters.add("year");
		allowedParameters.add("examType");
		if (RestImplUtils.validateParameters(allowedParameters, uriInfo)) {
			return getNextDecorator().getExamAdmissionsByYearAndExamType(year, examType);
		}

		allowedParameters.clear();
		allowedParameters.add("year");
		allowedParameters.add("examPeriodCode");
		if (RestImplUtils.validateParameters(allowedParameters, uriInfo)) {
			return getNextDecorator().getExamAdmissionsByYearAndExamPeriodCode(year, examPeriodCode);
		}

		allowedParameters.clear();
		allowedParameters.add("year");
		if (RestImplUtils.validateParameters(allowedParameters, uriInfo)) {
			return getNextDecorator().getExamAdmissionsByYear(year);
		}

		allowedParameters.clear();
		allowedParameters.add("examPeriodCode");
		if (RestImplUtils.validateParameters(allowedParameters, uriInfo)) {
			return getNextDecorator().getExamAdmissionsByExamPeriodCode(examPeriodCode);
		}

		allowedParameters.clear();
		allowedParameters.add("examType");
		if (RestImplUtils.validateParameters(allowedParameters, uriInfo)) {
			return getNextDecorator().getExamAdmissionsByExamType(examType);
		}

		return getNextDecorator().getExamAdmissions();
	}

}
