package za.ac.unisa.myadmin.service.studyquotation.impl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.studyquotation.StudyQuotationInfo;
import za.ac.unisa.myadmin.studyquotation.StudyUnit;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping({"/rest"})
public class StudyQuotationRestServiceImpl {

	@GetMapping(path = {"/studyfeequotation"})
	public StudyQuotationInfo calculateStudyFeeQuoation(@RequestParam(value = "academicYear", required = false) Integer academicYear,
														@RequestParam(value = "countryCode", required = false) String countryCode,
														@RequestParam(value = "qualificationType", required = false) String qualificationType,
														@RequestParam(value = "qualificationCode", required = false) String qualificationCode,
														@RequestParam(value = "libraryCard", required = false) String libraryCard,
														@RequestParam(value = "matricExemption", required = false) String matricExemption,
														@RequestParam(value = "courseCodes", required = false) List<String> courseCodes,
														@Context HttpServletRequest httpServletRequest) throws DoesNotExistException,
			MissingParameterException, InvalidParameterException, OperationFailedException {
		//Stub out
		StudyQuotationInfo studyQuotationInfo = new StudyQuotationInfo();
		studyQuotationInfo.setAcademicYear(academicYear);
		studyQuotationInfo.setCountryCode(countryCode);
		studyQuotationInfo.setQualificationType(qualificationType);
		studyQuotationInfo.setQualificationCode(qualificationCode);
		studyQuotationInfo.setLibraryCard(libraryCard);
		studyQuotationInfo.setMatricExemption(matricExemption);
		studyQuotationInfo.setStudyCodes(courseCodes);
		//
		List<StudyUnit> studyUnits = new ArrayList<>();
		StudyUnit unit = new StudyUnit();
		unit.setDescription("Test1");
		unit.setStudyUnitcode("Test1");
		unit.setFee(1000.00);//
		studyUnits.add(unit);
		unit.setDescription("Test 2");
		unit.setStudyUnitcode("Test 2");
		unit.setFee(1200.00);
		studyUnits.add(unit);
		studyQuotationInfo.setStudyUnits(studyUnits);
		return studyQuotationInfo;
	}
}
