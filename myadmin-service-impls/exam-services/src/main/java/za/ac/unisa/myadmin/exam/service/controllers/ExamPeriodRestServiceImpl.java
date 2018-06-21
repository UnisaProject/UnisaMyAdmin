package za.ac.unisa.myadmin.exam.service.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.exam.dto.ExamPeriodInfo;
import za.ac.unisa.myadmin.exam.service.ExamPeriodService;
import za.ac.unisa.myadmin.service.rest.impl.utils.RestImplUtils;

@RestController
@RequestMapping({ "/examservice" })
public class ExamPeriodRestServiceImpl {

	@Autowired
	@Qualifier("ExamPeriodServiceExclusionDecorator")
	private ExamPeriodService examPeriodService;

	@GetMapping(path = { "/examperiods/{code}" })
	public ExamPeriodInfo getExamPeriod(@PathVariable("code") Integer code) throws DoesNotExistException,
			MissingParameterException, InvalidParameterException, OperationFailedException {
		return examPeriodService.getExamPeriod(code);
	}

	@GetMapping(path = { "/examperiods" })
	public List<ExamPeriodInfo> searchForExamPeriods(
			@RequestParam(value = "codes", required = false) List<Integer> codes,
			HttpServletRequest httpServletRequest)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		Set<String> allowedParameters = new HashSet<>();

		allowedParameters.clear();
		allowedParameters.add("codes");
		if (RestImplUtils.validateParameters(allowedParameters, httpServletRequest.getParameterMap())) {
			return examPeriodService.getExamPeriodsByCodes(codes);
		}

		return examPeriodService.getExamPeriods();
	}

}
