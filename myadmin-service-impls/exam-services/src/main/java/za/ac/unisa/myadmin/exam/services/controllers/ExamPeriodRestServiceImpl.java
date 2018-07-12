package za.ac.unisa.myadmin.exam.services.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.core.UriInfo;

import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.exam.rest.services.ExamPeriodRestService;
import za.ac.unisa.myadmin.exam.services.dto.ExamPeriodInfo;
import za.ac.unisa.myadmin.service.base.decorators.ExamPeriodServiceDecorator;
import za.ac.unisa.myadmin.service.rest.impl.utils.RestImplUtils;

public class ExamPeriodRestServiceImpl extends ExamPeriodServiceDecorator implements ExamPeriodRestService {

	@Override
	public ExamPeriodInfo getExamPeriod(Integer code) throws DoesNotExistException, MissingParameterException,
			InvalidParameterException, OperationFailedException {
		return getNextDecorator().getExamPeriod(code);
	}

	@Override
	public List<ExamPeriodInfo> searchForExamPeriods(List<Integer> codes, UriInfo uriInfo)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		Set<String> allowedParameters = new HashSet<>();

		allowedParameters.clear();
		allowedParameters.add("code");
		if (RestImplUtils.validateParameters(allowedParameters, uriInfo)) {
			return getNextDecorator().getExamPeriodsByCodes(codes);
		}

		return getNextDecorator().getExamPeriods();
	}

}
