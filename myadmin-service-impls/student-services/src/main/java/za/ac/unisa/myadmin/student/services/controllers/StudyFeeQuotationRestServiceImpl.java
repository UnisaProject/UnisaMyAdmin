package za.ac.unisa.myadmin.student.services.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.student.services.dto.StudyFeeQuotationInfo;
import za.ac.unisa.myadmin.student.services.dto.StudyFeeQuotationRequestInfo;
import za.ac.unisa.myadmin.student.services.fees.StudyFeeQuotationService;

@RestController
@RequestMapping({ "/studentservices" })
public class StudyFeeQuotationRestServiceImpl {

	@Autowired
	@Qualifier("StudyFeeQuotationService")
	private StudyFeeQuotationService studyFeeQuotationService;

	@PostMapping(path = "/studyfeequotation")
	public StudyFeeQuotationInfo requestQuote(@RequestBody StudyFeeQuotationRequestInfo studyFeeQuotationRequest)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		return studyFeeQuotationService.requestQuote(studyFeeQuotationRequest);
	}

}
