package za.ac.unisa.myadmin.exam.service.controllers;

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
import za.ac.unisa.myadmin.exam.dto.ExaminationInfo;
import za.ac.unisa.myadmin.exam.service.ExaminationService;
import za.ac.unisa.myadmin.service.rest.impl.utils.RestImplUtils;

@RestController
@RequestMapping({ "/examservice" })
public class ExaminationRestServiceImpl {

	@Autowired
	@Qualifier("ExaminationServiceComplianceDecorator")
	private ExaminationService examinationService;

	@GetMapping(path = { "/examinations" })
	public List<ExaminationInfo> searchForExaminations(
			@RequestParam(value = "year", required = false) Integer year,
			@RequestParam(value = "examPeriodCode", required = false) Integer examPeriodCode,
			@RequestParam(value = "courseCodes", required = false) List<String> courseCodes,
			HttpServletRequest httpServletRequest)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		Set<String> allowedParameters = new HashSet<>();

		allowedParameters.clear();
		allowedParameters.add("year");
		allowedParameters.add("examPeriodCode");
		allowedParameters.add("courseCodes");
		if (RestImplUtils.validateParameters(allowedParameters, httpServletRequest.getParameterMap())) {
			return examinationService.getExaminationsByYearAndExamPeriodCodeAndCourseCodes(year, examPeriodCode,
					courseCodes);
		}

		allowedParameters.clear();
		allowedParameters.add("examPeriodCode");
		allowedParameters.add("courseCodes");
		if (RestImplUtils.validateParameters(allowedParameters, httpServletRequest.getParameterMap())) {
			return examinationService.getExaminationsByExamPeriodCodeAndCourseCodes(examPeriodCode, courseCodes);
		}

		allowedParameters.clear();
		allowedParameters.add("year");
		allowedParameters.add("courseCodes");
		if (RestImplUtils.validateParameters(allowedParameters, httpServletRequest.getParameterMap())) {
			return examinationService.getExaminationsByYearAndCourseCodes(year, courseCodes);
		}

		allowedParameters.clear();
		allowedParameters.add("year");
		allowedParameters.add("examPeriodCode");
		if (RestImplUtils.validateParameters(allowedParameters, httpServletRequest.getParameterMap())) {
			return examinationService.getExaminationsByYearAndExamPeriodCode(year, examPeriodCode);
		}

		allowedParameters.clear();
		allowedParameters.add("year");
		if (RestImplUtils.validateParameters(allowedParameters, httpServletRequest.getParameterMap())) {
			return examinationService.getExaminationsByYear(year);
		}

		allowedParameters.clear();
		allowedParameters.add("examPeriodCode");
		if (RestImplUtils.validateParameters(allowedParameters, httpServletRequest.getParameterMap())) {
			return examinationService.getExaminationsByExamPeriodCode(examPeriodCode);
		}

		allowedParameters.clear();
		allowedParameters.add("courseCodes");
		if (RestImplUtils.validateParameters(allowedParameters, httpServletRequest.getParameterMap())) {
			return examinationService.getExaminationsByCourseCodes(courseCodes);
		}

		throw new MissingParameterException();
	}

}
