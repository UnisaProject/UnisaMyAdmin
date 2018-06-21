package za.ac.unisa.myadmin.student.services.controllers;

import java.util.Date;
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
import za.ac.unisa.myadmin.service.rest.impl.utils.RestImplUtils;
import za.ac.unisa.myadmin.student.services.dto.RegistrationPeriodInfo;
import za.ac.unisa.myadmin.student.services.registration.RegistrationPeriodService;

@RestController
@RequestMapping({ "/studentservices" })
public class RegistrationPeriodRestServiceImpl {

	@Autowired
	@Qualifier("RegistrationPeriodService")
	private RegistrationPeriodService registrationPeriodService;

	@GetMapping(path = "/studyfeequotation/quotationYear")
	public int getValidQuotationYear() throws OperationFailedException {
		return registrationPeriodService.getValidQuotationYear();
	}

	// ***************************************

	@GetMapping(path = { "/registrationperiods" })
	public List<RegistrationPeriodInfo> searchForRegistrationPeriods(
			@RequestParam(value = "year", required = false) Integer year,
			@RequestParam(value = "semester", required = false) Integer semester,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "date", required = false) Date date, HttpServletRequest httpServletRequest)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		Set<String> allowedParameters = new HashSet<>();

		allowedParameters.clear();
		allowedParameters.add("year");
		allowedParameters.add("semester");
		allowedParameters.add("type");
		allowedParameters.add("date");
		if (RestImplUtils.validateParameters(allowedParameters, httpServletRequest.getParameterMap())) {
			return registrationPeriodService.getEffectiveRegistrationPeriodsByYearAndSemesterAndTypeOnDate(year,
					semester, type, date);
		}

		allowedParameters.clear();
		allowedParameters.add("year");
		allowedParameters.add("type");
		allowedParameters.add("date");
		if (RestImplUtils.validateParameters(allowedParameters, httpServletRequest.getParameterMap())) {
			return registrationPeriodService.getEffectiveRegistrationPeriodsByYearAndTypeOnDate(year, type, date);
		}

		allowedParameters.clear();
		allowedParameters.add("year");
		allowedParameters.add("semester");
		allowedParameters.add("date");
		if (RestImplUtils.validateParameters(allowedParameters, httpServletRequest.getParameterMap())) {
			return registrationPeriodService.getEffectiveRegistrationPeriodsByYearAndSemesterOnDate(year, semester,
					date);
		}

		allowedParameters.clear();
		allowedParameters.add("semester");
		allowedParameters.add("type");
		allowedParameters.add("date");
		if (RestImplUtils.validateParameters(allowedParameters, httpServletRequest.getParameterMap())) {
			return registrationPeriodService.getEffectiveRegistrationPeriodsBySemesterAndTypeOnDate(semester, type,
					date);
		}

		allowedParameters.clear();
		allowedParameters.add("year");
		allowedParameters.add("date");
		if (RestImplUtils.validateParameters(allowedParameters, httpServletRequest.getParameterMap())) {
			return registrationPeriodService.getEffectiveRegistrationPeriodsByYearOnDate(year, date);
		}

		allowedParameters.clear();
		allowedParameters.add("semester");
		allowedParameters.add("date");
		if (RestImplUtils.validateParameters(allowedParameters, httpServletRequest.getParameterMap())) {
			return registrationPeriodService.getEffectiveRegistrationPeriodsBySemesterOnDate(semester, date);
		}

		allowedParameters.clear();
		allowedParameters.add("type");
		allowedParameters.add("date");
		if (RestImplUtils.validateParameters(allowedParameters, httpServletRequest.getParameterMap())) {
			return registrationPeriodService.getEffectiveRegistrationPeriodsByTypeOnDate(type, date);
		}

		allowedParameters.clear();
		allowedParameters.add("year");
		allowedParameters.add("semester");
		allowedParameters.add("type");
		if (RestImplUtils.validateParameters(allowedParameters, httpServletRequest.getParameterMap())) {
			return registrationPeriodService.getRegistrationPeriodsByYearAndSemesterAndType(year, semester, type);
		}

		allowedParameters.clear();
		allowedParameters.add("year");
		allowedParameters.add("semester");
		if (RestImplUtils.validateParameters(allowedParameters, httpServletRequest.getParameterMap())) {
			return registrationPeriodService.getRegistrationPeriodsByYearAndSemester(year, semester);
		}

		allowedParameters.clear();
		allowedParameters.add("year");
		allowedParameters.add("type");
		if (RestImplUtils.validateParameters(allowedParameters, httpServletRequest.getParameterMap())) {
			return registrationPeriodService.getRegistrationPeriodsByYearAndType(year, type);
		}

		allowedParameters.clear();
		allowedParameters.add("semester");
		allowedParameters.add("type");
		if (RestImplUtils.validateParameters(allowedParameters, httpServletRequest.getParameterMap())) {
			return registrationPeriodService.getRegistrationPeriodsBySemesterAndType(semester, type);
		}

		allowedParameters.clear();
		allowedParameters.add("year");
		if (RestImplUtils.validateParameters(allowedParameters, httpServletRequest.getParameterMap())) {
			return registrationPeriodService.getRegistrationPeriodsByYear(year);
		}

		allowedParameters.clear();
		allowedParameters.add("semester");
		if (RestImplUtils.validateParameters(allowedParameters, httpServletRequest.getParameterMap())) {
			return registrationPeriodService.getRegistrationPeriodsBySemester(semester);
		}

		allowedParameters.clear();
		allowedParameters.add("type");
		if (RestImplUtils.validateParameters(allowedParameters, httpServletRequest.getParameterMap())) {
			return registrationPeriodService.getRegistrationPeriodsByType(type);
		}

		throw new MissingParameterException();
	}

}
