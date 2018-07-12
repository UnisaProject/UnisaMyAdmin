package za.ac.unisa.myadmin.exam.services.controllers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.core.UriInfo;

import za.ac.unisa.myadmin.exam.rest.services.ExamAdmissionRestService;
import za.ac.unisa.myadmin.service.rest.impl.utils.RestImplUtils;

public class ExamAdmissionRestServiceImpl implements ExamAdmissionRestService {

	@Override
	public List<String> searchForExamAdmissions(Integer year, Integer examPeriodCode, Integer examType, UriInfo uriInfo)
			throws Exception {
		Set<String> allowedParameters = new HashSet<String>();

		allowedParameters.clear();
		allowedParameters.add("year");
		allowedParameters.add("examPeriodCode");
		allowedParameters.add("examType");
		if (RestImplUtils.validateParameters(allowedParameters, uriInfo)) {
			return Arrays.asList("year", "examPeriodCode", "examType");
		}

		allowedParameters.clear();
		allowedParameters.add("examPeriodCode");
		allowedParameters.add("examType");
		if (RestImplUtils.validateParameters(allowedParameters, uriInfo)) {
			return Arrays.asList("examPeriodCode", "examType");
		}

		allowedParameters.clear();
		allowedParameters.add("year");
		allowedParameters.add("examType");
		if (RestImplUtils.validateParameters(allowedParameters, uriInfo)) {
			return Arrays.asList("year", "examType");
		}

		allowedParameters.clear();
		allowedParameters.add("year");
		allowedParameters.add("examPeriodCode");
		if (RestImplUtils.validateParameters(allowedParameters, uriInfo)) {
			return Arrays.asList("year", "examPeriodCode");
		}

		allowedParameters.clear();
		allowedParameters.add("year");
		if (RestImplUtils.validateParameters(allowedParameters, uriInfo)) {
			return Arrays.asList("year");
		}

		allowedParameters.clear();
		allowedParameters.add("examPeriodCode");
		if (RestImplUtils.validateParameters(allowedParameters, uriInfo)) {
			return Arrays.asList("examPeriodCode");
		}

		allowedParameters.clear();
		allowedParameters.add("examType");
		if (RestImplUtils.validateParameters(allowedParameters, uriInfo)) {
			return Arrays.asList("examType");
		}

		return Arrays.asList("alles");
	}

}
