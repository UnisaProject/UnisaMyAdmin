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
import za.ac.unisa.myadmin.exam.dto.ExamPaperInfo;
import za.ac.unisa.myadmin.exam.service.ExamPaperService;
import za.ac.unisa.myadmin.service.rest.impl.utils.RestImplUtils;

@RestController
@RequestMapping({ "/examservice" })
public class ExamPaperRestServiceImpl {

	@Autowired
	@Qualifier("ExamPaperServiceComplianceDecorator")
	private ExamPaperService examPaperService;

	@GetMapping(path = { "/exampapers" })
	public List<ExamPaperInfo> searchForExamPeriodDates(
			@RequestParam(value = "year", required = false) Integer year,
			@RequestParam(value = "courseCodes", required = false) List<String> courseCodes,
			HttpServletRequest httpServletRequest)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		Set<String> allowedParameters = new HashSet<String>();

		allowedParameters.clear();
		allowedParameters.add("year");
		allowedParameters.add("courseCodes");
		if (RestImplUtils.validateParameters(allowedParameters, httpServletRequest.getParameterMap())) {
			return examPaperService.getExamPapersByYearAndCourseCodes(year, courseCodes);
		}

		allowedParameters.clear();
		allowedParameters.add("year");
		if (RestImplUtils.validateParameters(allowedParameters, httpServletRequest.getParameterMap())) {
			return examPaperService.getExamPapersByYear(year);
		}

		allowedParameters.clear();
		allowedParameters.add("courseCodes");
		if (RestImplUtils.validateParameters(allowedParameters, httpServletRequest.getParameterMap())) {
			return examPaperService.getExamPapersByCourseCodes(courseCodes);
		}

		throw new MissingParameterException();
	}

}
