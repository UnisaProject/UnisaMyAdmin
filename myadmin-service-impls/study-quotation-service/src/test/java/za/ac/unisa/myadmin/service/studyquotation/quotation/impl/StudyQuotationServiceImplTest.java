package za.ac.unisa.myadmin.service.studyquotation.quotation.impl;

import org.junit.Ignore;
import org.junit.Test;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.studyquotation.quotation.StudyQuotationInfo;
import za.ac.unisa.myadmin.studyquotation.quotation.StudyQuotationRequestInfo;
import za.ac.unisa.myadmin.studyquotation.quotation.StudyUnitInfo;

import java.util.Arrays;
import java.util.List;

@Ignore
public class StudyQuotationServiceImplTest {


    public StudyQuotationRequestInfo createRequest(){
        StudyQuotationRequestInfo request = new StudyQuotationRequestInfo();
        request.setStudyCodes(Arrays.asList("EDPHOD8","EDAHOD5","EDDHODJ", "PFC104T"));
        request.setQualificationCode("02623");
         request.setQualification("99999");
        request.setCountryCode("1015");
        request.setAcademicYear((short)2018);
        request.setMatricExemption(true);
        request.setLibraryCard(true);
        return request;
    }

    @Test
    public void testGetQuotation() throws OperationFailedException {
        StudyQuotationServiceImpl studyQuotationService = new StudyQuotationServiceImpl();
        StudyQuotationInfo quotation = studyQuotationService.calculateStudyQuotation(createRequest());
        printQuotation(quotation);
    }

    private void printQuotation(StudyQuotationInfo quotation){
        System.out.println(String.format("Academic year      : %s", quotation.getAcademicYear()));
        System.out.println(String.format("Qualification code : %s", quotation.getQualificationCode()));
        System.out.println("Study fees:");
        System.out.println("----------------------------------------");
        List<StudyUnitInfo> studyUnitInfos = quotation.getStudyUnitInfos();
        for(StudyUnitInfo studyUnitInfo : studyUnitInfos){
            System.out.println(String.format("%s %s : %s", studyUnitInfo.getStudyUnitcode(), studyUnitInfo.getDescription(), studyUnitInfo.getFee()));
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
