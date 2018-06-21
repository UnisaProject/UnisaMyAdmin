package za.ac.unisa.myadmin.student.services.impls;

import java.util.Arrays;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import za.ac.unisa.myadmin.student.services.dto.StudyFeeQuotationInfo;
import za.ac.unisa.myadmin.student.services.dto.StudyFeeQuotationRequestInfo;
import za.ac.unisa.myadmin.student.services.dto.StudyUnitInfo;
import za.ac.unisa.myadmin.student.services.impls.StudyFeeQuotationServiceImpl;

@Ignore
public class StudyFeeQuotationServiceImplTest {


    public StudyFeeQuotationRequestInfo createRequest(){
        StudyFeeQuotationRequestInfo request = new StudyFeeQuotationRequestInfo();
        request.setCourseCodes(Arrays.asList("EDPHOD8","EDAHOD5","EDDHODJ", "PFC104T"));
        request.setQualificationCode("02623");
         request.setQualification("99999");
        request.setCountryCode("1015");
		request.setAcademicYear(2018);
        request.setMatricExemption(true);
        request.setLibraryCard(true);
        return request;
    }

    @Test
	public void testGetQuotation() throws Exception {
		StudyFeeQuotationServiceImpl studyFeeQuotationService = new StudyFeeQuotationServiceImpl();
		StudyFeeQuotationInfo quotation = studyFeeQuotationService.requestQuote(createRequest());
        printQuotation(quotation);
    }

    private void printQuotation(StudyFeeQuotationInfo quotation){
        System.out.println(String.format("Academic year      : %s", quotation.getAcademicYear()));
        System.out.println(String.format("Qualification code : %s", quotation.getQualificationCode()));
        System.out.println("Study fees:");
        System.out.println("----------------------------------------");
		List<StudyUnitInfo> studyUnitInfos = quotation.getStudyUnits();
        for(StudyUnitInfo studyUnitInfo : studyUnitInfos){
			System.out.println(String.format("%s %s : %s", studyUnitInfo.getCode(), studyUnitInfo.getDescription(),
					studyUnitInfo.getFee()));
        }
        System.out.println("----------------------------------------");
        System.out.println(String.format("Levy for foreign countries    : %s", quotation.getForeignLevy()));
        System.out.println(String.format("Library Access Card           : %s", quotation.getLibraryCardCost()));
        System.out.println(String.format("Matriculation Exemption Fee   : %s", quotation.getMatricExemptionCost()));
        System.out.println("----------------------------------------");
        System.out.println(String.format("Total                         : %s", quotation.getTotalFee()));
        System.out.println("----------------------------------------");
        System.out.println(String.format("Payment due with registration : %s", quotation.getPaymentDue()));
    }
}
