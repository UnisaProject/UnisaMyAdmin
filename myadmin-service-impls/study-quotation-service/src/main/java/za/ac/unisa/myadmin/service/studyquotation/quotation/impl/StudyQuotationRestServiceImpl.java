package za.ac.unisa.myadmin.service.studyquotation.quotation.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.studyquotation.quotation.StudyQuotation;
import za.ac.unisa.myadmin.studyquotation.quotation.StudyQuotationRequest;
import za.ac.unisa.myadmin.studyquotation.quotation.StudyQuotationService;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import java.util.List;

@RestController
@RequestMapping({ "/rest" })
public class StudyQuotationRestServiceImpl {

	@Autowired
	@Qualifier("StudyQuotationService")
	private StudyQuotationService studyQuotationService;

	@GetMapping(path = "/studyfeequotation/calculateQuotation")
	public StudyQuotation calculateStudyFeeQuoation(@RequestParam(value = "academicYear", required = false) Integer academicYear,
													@RequestParam(value = "countryCode", required = false) String countryCode,
													@RequestParam(value = "qualificationType", required = false) String qualificationType,
													@RequestParam(value = "qualificationCode", required = false) String qualificationCode,
													@RequestParam(value = "libraryCard", required = false) boolean libraryCard,
													@RequestParam(value = "matricExemption", required = false) boolean matricExemption,
													@RequestParam(value = "courseCodes", required = false) List<String> courseCodes,
													@Context HttpServletRequest httpServletRequest) throws DoesNotExistException,
			MissingParameterException, InvalidParameterException, OperationFailedException {
		//Stub out
		StudyQuotationRequest studyQuotationInfo = new StudyQuotationRequest();
		studyQuotationInfo.setAcademicYear(academicYear);
		studyQuotationInfo.setCountryCode(countryCode);
		studyQuotationInfo.setQualification(qualificationType);
		studyQuotationInfo.setQualificationCode(qualificationCode);
		studyQuotationInfo.setLibraryCard(libraryCard);
		studyQuotationInfo.setMatricExemption(matricExemption);
		studyQuotationInfo.setStudyCodes(courseCodes);

		return studyQuotationService.calculateStudyQuotation(studyQuotationInfo);
	}

	@GetMapping(path = "/studyfeequotation/quotationYear")
	public int getValidQuotationYear(){
		return 2018; // TODO stub
	}
}
