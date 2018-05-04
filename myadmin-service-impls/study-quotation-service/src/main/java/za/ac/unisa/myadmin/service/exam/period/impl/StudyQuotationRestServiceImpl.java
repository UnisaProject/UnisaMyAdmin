package za.ac.unisa.myadmin.service.exam.period.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.studyquotation.quotation.StudyQuotation;
import za.ac.unisa.myadmin.studyquotation.quotation.StudyQuotationRequest;
import za.ac.unisa.myadmin.studyquotation.quotation.StudyQuotationService;

@RestController
@RequestMapping({ "/rest" })
public class StudyQuotationRestServiceImpl {

	@Autowired
	@Qualifier("StudyQuotationService")
	private StudyQuotationService studyQuotationService;

	@PostMapping(path = { "/studyQuotation/calculateQuotation" })
	public StudyQuotation getQuotation(StudyQuotationRequest studyQuotationRequest) throws OperationFailedException {
		return this.studyQuotationService.calculateStudyQuotation(studyQuotationRequest);
	}

}
