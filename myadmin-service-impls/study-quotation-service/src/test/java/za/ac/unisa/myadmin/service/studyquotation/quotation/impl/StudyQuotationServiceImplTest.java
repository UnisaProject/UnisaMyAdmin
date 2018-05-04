package za.ac.unisa.myadmin.service.studyquotation.quotation.impl;

import org.junit.Test;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.service.studyquotation.quotation.impl.StudyQuotationServiceImpl;
import za.ac.unisa.myadmin.studyquotation.quotation.StudyQuotation;
import za.ac.unisa.myadmin.studyquotation.quotation.StudyQuotationRequest;

import java.util.Arrays;

public class StudyQuotationServiceImplTest {


    public StudyQuotationRequest createRequest(){
        StudyQuotationRequest request = new StudyQuotationRequest();
        request.setStudyCodes(Arrays.asList("a", "b"));
        request.setQualification("qual");
        request.setQualificationCode("aa");
        request.setCountryCode("1015");
        request.setAcademicYear((short)2018);
        request.setMatricExemption(true);
        request.setLibraryCard(true);
        return request;
    }

    @Test
    public void testGetQuotation() throws OperationFailedException {
        StudyQuotationServiceImpl studyQuotationService = new StudyQuotationServiceImpl();
        StudyQuotation quotation = studyQuotationService.calculateStudyQuotation(createRequest());
    }
}
