package za.ac.unisa.myadmin.service.exam.period.date.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.exam.period.date.ExamPeriodDateInfo;
import za.ac.unisa.myadmin.exam.period.date.ExamPeriodDateService;
import za.ac.unisa.myadmin.service.rest.impl.utils.RestImplUtils;

@RestController
@RequestMapping({ "/rest" })
public class ExamPeriodDateRestServiceImpl {

	@Autowired
	private ExamPeriodDateService examPeriodDateService;

	@GetMapping(path = { "/examperioddates" })
	public List<ExamPeriodDateInfo> searchForExamPeriodDates(
			@RequestParam(value = "year", required = false) Integer year,
			@RequestParam(value = "examPeriodCode", required = false) Integer examPeriodCode,
			@RequestParam(value = "courseCodes", required = false) List<String> courseCodes,
			@Context HttpServletRequest httpServletRequest)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		Set<String> allowedParameters = new HashSet<>();

		allowedParameters.clear();
		allowedParameters.add("year");
		allowedParameters.add("examPeriodCode");
		allowedParameters.add("courseCodes");
		courseCodes = courseCodes.stream()
				.map(String::toUpperCase)
				.collect(Collectors.toList());
		if (RestImplUtils.validateParameters(allowedParameters, httpServletRequest)) {
			return examPeriodDateService.getExamPeriodDatesByYearAndExamPeriodCodeAndCourseCodes(year, examPeriodCode,
					courseCodes);
		}

		allowedParameters.clear();
		allowedParameters.add("examPeriodCode");
		allowedParameters.add("courseCodes");
		if (RestImplUtils.validateParameters(allowedParameters, httpServletRequest)) {
			return examPeriodDateService.getExamPeriodDatesByExamPeriodCodeAndCourseCodes(examPeriodCode, courseCodes);
		}

		allowedParameters.clear();
		allowedParameters.add("year");
		allowedParameters.add("courseCodes");
		if (RestImplUtils.validateParameters(allowedParameters, httpServletRequest)) {
			return examPeriodDateService.getExamPeriodDatesByYearAndCourseCodes(year, courseCodes);
		}

		allowedParameters.clear();
		allowedParameters.add("year");
		allowedParameters.add("examPeriodCode");
		if (RestImplUtils.validateParameters(allowedParameters, httpServletRequest)) {
			return examPeriodDateService.getExamPeriodDatesByYearAndExamPeriodCode(year, examPeriodCode);
		}

		allowedParameters.clear();
		allowedParameters.add("year");
		if (RestImplUtils.validateParameters(allowedParameters, httpServletRequest)) {
			return examPeriodDateService.getExamPeriodDatesByYear(year);
		}

		allowedParameters.clear();
		allowedParameters.add("examPeriodCode");
		if (RestImplUtils.validateParameters(allowedParameters, httpServletRequest)) {
			return examPeriodDateService.getExamPeriodDatesByExamPeriodCode(examPeriodCode);
		}

		allowedParameters.clear();
		allowedParameters.add("courseCodes");
		if (RestImplUtils.validateParameters(allowedParameters, httpServletRequest)) {
			return examPeriodDateService.getExamPeriodDatesByCourseCodes(courseCodes);
		}

		throw new MissingParameterException();
	}

}
