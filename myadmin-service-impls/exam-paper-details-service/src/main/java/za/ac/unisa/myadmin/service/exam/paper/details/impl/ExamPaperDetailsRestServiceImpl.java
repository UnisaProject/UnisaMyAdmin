package za.ac.unisa.myadmin.service.exam.paper.details.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import za.ac.unisa.myadmin.exam.paper.details.ExamPaperDetailsInfo;
import za.ac.unisa.myadmin.exam.paper.details.ExamPaperDetailsService;
import za.ac.unisa.myadmin.service.rest.impl.utils.RestImplUtils;

@RestController
@RequestMapping({ "/rest" })
public class ExamPaperDetailsRestServiceImpl {

	@Autowired
	private ExamPaperDetailsService examPaperDetailsService;

	@GetMapping(path = { "/exampaperdetails" })
	public List<ExamPaperDetailsInfo> searchForExamPeriodDates(
			@RequestParam(value = "year", required = false) Integer year,
			@RequestParam(value = "courseCodes", required = false) List<String> courseCodes,
			@Context HttpServletRequest httpServletRequest)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		Set<String> allowedParameters = new HashSet<String>();

		allowedParameters.clear();
		allowedParameters.add("year");
		allowedParameters.add("courseCodes");
		if (RestImplUtils.validateParameters(allowedParameters, httpServletRequest)) {
			return examPaperDetailsService.getPaperDetailsByYearAndCourseCodes(year, courseCodes);
		}

		allowedParameters.clear();
		allowedParameters.add("year");
		if (RestImplUtils.validateParameters(allowedParameters, httpServletRequest)) {
			return examPaperDetailsService.getPaperDetailsByYear(year);
		}

		allowedParameters.clear();
		allowedParameters.add("courseCodes");
		if (RestImplUtils.validateParameters(allowedParameters, httpServletRequest)) {
			return examPaperDetailsService.getPaperDetailsByCourseCodes(courseCodes);
		}

		throw new MissingParameterException();
	}

}
