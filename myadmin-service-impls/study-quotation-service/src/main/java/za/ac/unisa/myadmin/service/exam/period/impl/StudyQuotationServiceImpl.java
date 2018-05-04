package za.ac.unisa.myadmin.service.exam.period.impl;

import Srrqn01h.Abean.Srrqn01sQuoteStudyFees;
import za.ac.unisa.myadmin.studyquotation.quotation.StudyQuotation;
import za.ac.unisa.myadmin.studyquotation.quotation.StudyUnit;

import java.beans.PropertyVetoException;
import java.util.List;
import java.util.Vector;

public class StudyQuotationServiceImpl {


	private Srrqn01sQuoteStudyFees srrqn01sQuoteStudyFees;

	public static final int QUAL_CODE_MISSING = 0;
	public static final int COOLGEN_ERROR = 1;
	public static final int NO_ERRORS = 2;

	public void init() throws PropertyVetoException {
		srrqn01sQuoteStudyFees = new Srrqn01sQuoteStudyFees();
		srrqn01sQuoteStudyFees.clear();
		srrqn01sQuoteStudyFees.setInWsUserNumber(9999);
		srrqn01sQuoteStudyFees.setInWsWorkstationCode("internet");
		srrqn01sQuoteStudyFees.setInCsfClientServerCommunicationsClientVersionNumber((short) 3);
		srrqn01sQuoteStudyFees.setInCsfClientServerCommunicationsClientRevisionNumber((short) 1);
		srrqn01sQuoteStudyFees.setInCsfClientServerCommunicationsAction("P");
		srrqn01sQuoteStudyFees.setInCsfClientServerCommunicationsClientDevelopmentPhase("C");
		srrqn01sQuoteStudyFees.setInStudentAnnualRecordMkStudentNr(0);
		srrqn01sQuoteStudyFees.setInWsStudentSurname("A");
		srrqn01sQuoteStudyFees.setInWsStudentInitials("A");
		srrqn01sQuoteStudyFees.setInWsStudentMkCorrespondenceLanguage("E");
		srrqn01sQuoteStudyFees.setInWsAddressPostalCode((short) 1);
	}

	public int calculateStudyQuotation(StudyQuotation studyQuotation) throws PropertyVetoException, Exception {
		init();
		srrqn01sQuoteStudyFees.setInStudentAnnualRecordMkAcademicYear(studyQuotation.getAcademicYear());

		//if ((studyQuotation.getQualification().equalsIgnoreCase("99999")) && (studyQuotation.getQualificationCode().equalsIgnoreCase("00000"))) {
		if ((studyQuotation.getQualification().equalsIgnoreCase("99999")) && (studyQuotation.getQualificationCode().equalsIgnoreCase(""))) {
			studyQuotation.reset();
			return QUAL_CODE_MISSING;
			//} else if ((studyQuotation.getQualification().equalsIgnoreCase("99999")) && (!studyQuotation.getQualificationCode().equalsIgnoreCase("00000"))) {
		} else if ((studyQuotation.getQualification().equalsIgnoreCase("99999")) && (!studyQuotation.getQualificationCode().equalsIgnoreCase(""))) {
			srrqn01sQuoteStudyFees.setInStudentAcademicRecordMkQualificationCode(
					studyQuotation.getQualificationCode()
			);
			studyQuotation.setQualification(studyQuotation.getQualificationCode());
		} else {
			srrqn01sQuoteStudyFees.setInStudentAcademicRecordMkQualificationCode(
					studyQuotation.getQualification()
			);
		}

		srrqn01sQuoteStudyFees.setInWsCountryCode(studyQuotation.getCountryCode());
		srrqn01sQuoteStudyFees.setInSmartcardIefSuppliedFlag(studyQuotation.getLibraryCard().toString());
		srrqn01sQuoteStudyFees.setInMatrExemptionIefSuppliedFlag(studyQuotation.getMatricExemption());

		List<String> studyCodes = studyQuotation.getStudyCodes();
		for(int idx = 0 ; idx < studyCodes.size(); idx++){
			String code = studyCodes.get(idx);
			srrqn01sQuoteStudyFees.setInGStudentStudyUnitMkStudyUnitCode(idx+1, code.toUpperCase());
		}
		srrqn01sQuoteStudyFees.execute();

		String errorMessage = srrqn01sQuoteStudyFees.getOutCsfStringsString500();
		if (!"Error reading study unit cost information".equalsIgnoreCase(errorMessage)){
			setErrorMessage(errorMessage);
			if ((errorMessage != null) && (!errorMessage.equals(""))) {
				studyQuotation.reset();
				return COOLGEN_ERROR;
			}
		}

		setCount(srrqn01sQuoteStudyFees.getOutGroupCount());
		setExitStateType(srrqn01sQuoteStudyFees.getExitStateType());
		setExitStateMessage(srrqn01sQuoteStudyFees.getExitStateMsg());

		if (exception.getException() != null) {
			throw exception.getException();
		} else if (getExitStateType() < 3) {
			throw new Exception(getExitStateMessage());
		}

		Vector studyUnits = new Vector();
		for(int i=0; i < (count-1) ; i++){
			StudyUnit studyUnit = new StudyUnit();
			studyUnit.setStudyUnitcode(srrqn01sQuoteStudyFees.getOutGInternetWsStudyUnitCode(i));
			studyUnit.setDescription(srrqn01sQuoteStudyFees.getOutGInternetWsStudyUnitEngShortDescription(i));
			studyUnit.setFee(srrqn01sQuoteStudyFees.getOutGStudyUnitCostIefSuppliedTotalCurrency(i));
			studyUnits.add(studyUnit);
		}
		studyQuotation.setStudyUnits(studyUnits);
		studyQuotation.setForeignLevy(srrqn01sQuoteStudyFees.getOutForeignLevyIefSuppliedTotalCurrency());
		studyQuotation.setLibraryCardCost(srrqn01sQuoteStudyFees.getOutSmartcardIefSuppliedTotalCurrency());
		studyQuotation.setMatricExemptionCost(srrqn01sQuoteStudyFees.getOutMatrExemptionIefSuppliedTotalCurrency());
		studyQuotation.setTotalFee(srrqn01sQuoteStudyFees.getOutTotalIefSuppliedTotalCurrency());
		studyQuotation.setPaymentDue(srrqn01sQuoteStudyFees.getOutRegPaymentIefSuppliedTotalCurrency());
		studyQuotation.setPrescribedBooks(srrqn01sQuoteStudyFees.getOutWsPrescribedBooksAmount());

		return NO_ERRORS;
	}
}
