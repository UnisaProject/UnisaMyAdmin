package za.ac.unisa.myadmin.exam.services.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.exam.services.ExamAdmissionService;
import za.ac.unisa.myadmin.exam.services.dto.ExamAdmissionInfo;
import za.ac.unisa.myadmin.service.rest.impl.utils.RestImplUtils;

@RestController
@RequestMapping({ "/examservices" })
public class ExamAdmissionRestServiceImpl {

	@Autowired
	@Qualifier("ExamAdmissionServiceYearCalculationDecorator")
	private ExamAdmissionService examAdmissionService;

	@GetMapping(path = { "/examadmissions" })
	public List<ExamAdmissionInfo> searchForExamAdmissions(@RequestParam(value = "year", required = false) Integer year,
			@RequestParam(value = "examPeriodCode", required = false) Integer examPeriodCode,
			@RequestParam(value = "examType", required = false) Integer examType,
			HttpServletRequest httpServletRequest)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		Set<String> allowedParameters = new HashSet<String>();

		allowedParameters.clear();
		allowedParameters.add("year");
		allowedParameters.add("examPeriodCode");
		allowedParameters.add("examType");
		if (RestImplUtils.validateParameters(allowedParameters, httpServletRequest.getParameterMap())) {
			return examAdmissionService.getExamAdmissionsByYearAndExamPeriodCodeAndExamType(year, examPeriodCode,
					examType);
		}

		allowedParameters.clear();
		allowedParameters.add("examPeriodCode");
		allowedParameters.add("examType");
		if (RestImplUtils.validateParameters(allowedParameters, httpServletRequest.getParameterMap())) {
			return examAdmissionService.getExamAdmissionsByExamPeriodCodeAndExamType(examPeriodCode, examType);
		}

		allowedParameters.clear();
		allowedParameters.add("year");
		allowedParameters.add("examType");
		if (RestImplUtils.validateParameters(allowedParameters, httpServletRequest.getParameterMap())) {
			return examAdmissionService.getExamAdmissionsByYearAndExamType(year, examType);
		}

		allowedParameters.clear();
		allowedParameters.add("year");
		allowedParameters.add("examPeriodCode");
		if (RestImplUtils.validateParameters(allowedParameters, httpServletRequest.getParameterMap())) {
			return examAdmissionService.getExamAdmissionsByYearAndExamPeriodCode(year, examPeriodCode);
		}

		allowedParameters.clear();
		allowedParameters.add("year");
		if (RestImplUtils.validateParameters(allowedParameters, httpServletRequest.getParameterMap())) {
			return examAdmissionService.getExamAdmissionsByYear(year);
		}

		allowedParameters.clear();
		allowedParameters.add("examPeriodCode");
		if (RestImplUtils.validateParameters(allowedParameters, httpServletRequest.getParameterMap())) {
			return examAdmissionService.getExamAdmissionsByExamPeriodCode(examPeriodCode);
		}

		allowedParameters.clear();
		allowedParameters.add("examType");
		if (RestImplUtils.validateParameters(allowedParameters, httpServletRequest.getParameterMap())) {
			return examAdmissionService.getExamAdmissionsByExamType(examType);
		}

		return examAdmissionService.getExamAdmissions();
	}

}
