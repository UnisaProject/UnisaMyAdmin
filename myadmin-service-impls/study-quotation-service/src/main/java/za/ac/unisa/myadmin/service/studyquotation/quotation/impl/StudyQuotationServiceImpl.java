package za.ac.unisa.myadmin.service.studyquotation.quotation.impl;

import Srrqn01h.Abean.Srrqn01sQuoteStudyFees;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.studyquotation.quotation.StudyQuotationInfo;
import za.ac.unisa.myadmin.studyquotation.quotation.StudyQuotationRequestInfo;
import za.ac.unisa.myadmin.studyquotation.quotation.StudyQuotationService;
import za.ac.unisa.myadmin.studyquotation.quotation.StudyUnitInfo;

import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service("StudyQuotationService")
public class StudyQuotationServiceImpl implements StudyQuotationService {

	/**
	 * Get an instance of the StudyFee proxy
	 *
	 * @return
	 * @throws PropertyVetoException
	 */
	private Srrqn01sQuoteStudyFees getProxyInstance() throws PropertyVetoException {
		Srrqn01sQuoteStudyFees studyFeeProxy = new Srrqn01sQuoteStudyFees();
		studyFeeProxy.clear();
		studyFeeProxy.setInWsUserNumber(9999);
		studyFeeProxy.setInWsWorkstationCode("internet");
		studyFeeProxy.setInCsfClientServerCommunicationsClientVersionNumber((short) 3);
		studyFeeProxy.setInCsfClientServerCommunicationsClientRevisionNumber((short) 1);
		studyFeeProxy.setInCsfClientServerCommunicationsAction("P");
		studyFeeProxy.setInCsfClientServerCommunicationsClientDevelopmentPhase("C");
		studyFeeProxy.setInStudentAnnualRecordMkStudentNr(0);
		studyFeeProxy.setInWsStudentSurname("A");
		studyFeeProxy.setInWsStudentInitials("A");
		studyFeeProxy.setInWsStudentMkCorrespondenceLanguage("E");
		studyFeeProxy.setInWsAddressPostalCode((short) 1);
		return studyFeeProxy;
	}

	/**
	 * Populate all the input parameters from the <code>StudyQuotationRequestInfo</code> onto the proxy class
	 *
	 * @param studyFeeProxy The proxy to populate the inputs to
	 * @param request       The quotation request to copy the inputs from.
	 * @throws PropertyVetoException
	 * @throws OperationFailedException
	 */
	private void populateInputs(Srrqn01sQuoteStudyFees studyFeeProxy, StudyQuotationRequestInfo request) throws PropertyVetoException, OperationFailedException {
		studyFeeProxy.setInStudentAnnualRecordMkAcademicYear((short) request.getAcademicYear());

		final String POSTGRAD = "99999";
		final String inputQualification = request.getQualification();
		final String inputQualificationCode = request.getQualificationCode();

		// If both the qualification and qualification code is unset
		if ((POSTGRAD.equalsIgnoreCase(inputQualification)) && ("".equalsIgnoreCase(inputQualificationCode))) {
			throw new OperationFailedException("Qualification code must be entered");
		}
		// If the qualification is unset, but the qualification code is set
		else if ((POSTGRAD.equalsIgnoreCase(inputQualification)) && (!"".equalsIgnoreCase(inputQualificationCode))) {
			studyFeeProxy.setInStudentAcademicRecordMkQualificationCode(inputQualificationCode);
			request.setQualification(inputQualification);
		}
		// Else both qualification and qualification code is set
		else {
			studyFeeProxy.setInStudentAcademicRecordMkQualificationCode(inputQualificationCode);
		}

		studyFeeProxy.setInWsCountryCode(request.getCountryCode());
		studyFeeProxy.setInSmartcardIefSuppliedFlag(request.isLibraryCard() ? "Y" : "N");
		studyFeeProxy.setInMatrExemptionIefSuppliedFlag(request.isMatricExemption() ? "Y" : "N");

		List<String> studyCodes = request.getStudyCodes();
		for (int idx = 0; idx < studyCodes.size(); idx++) {
			String code = studyCodes.get(idx);
			studyFeeProxy.setInGStudentStudyUnitMkStudyUnitCode(idx + 1, code.toUpperCase());
		}
	}

	/**
	 * Build a study quotation using the input request, and the response from the proxy execution.
	 *
	 * @param studyFeeProxy The <code>Srrqn01sQuoteStudyFees</code> proxy that was used to execute the request
	 * @param request       The <code>StudyQuotationRequestInfo</code> request used to request the quotation.
	 * @return A <code>StudyQuotationInfo</code> object.
	 * @throws OperationFailedException
	 */
	private StudyQuotationInfo buildResponse(Srrqn01sQuoteStudyFees studyFeeProxy, StudyQuotationRequestInfo request) throws OperationFailedException {
		final StudyQuotationInfo responseQuotation = new StudyQuotationInfo(request);

		String errorMessage = studyFeeProxy.getOutCsfStringsString500();
		if (!"Error reading study unit cost information".equalsIgnoreCase(errorMessage) && StringUtils.hasText(errorMessage)) {
			throw new OperationFailedException(errorMessage);
		}
		else if(StringUtils.hasText(errorMessage)){
			responseQuotation.setCoolgenErrorMsg(errorMessage);
		}

		for (int i = 0; i < (studyFeeProxy.getOutGroupCount() - 1); i++) {
			StudyUnitInfo studyUnitInfo = new StudyUnitInfo();
			studyUnitInfo.setStudyUnitcode(studyFeeProxy.getOutGInternetWsStudyUnitCode(i));
			studyUnitInfo.setDescription(studyFeeProxy.getOutGInternetWsStudyUnitEngShortDescription(i));
			studyUnitInfo.setFee(studyFeeProxy.getOutGStudyUnitCostIefSuppliedTotalCurrency(i));
			responseQuotation.addStudyUnit(studyUnitInfo);
		}
		responseQuotation.setForeignLevy(studyFeeProxy.getOutForeignLevyIefSuppliedTotalCurrency());
		responseQuotation.setLibraryCardCost(studyFeeProxy.getOutSmartcardIefSuppliedTotalCurrency());
		responseQuotation.setMatricExemptionCost(studyFeeProxy.getOutMatrExemptionIefSuppliedTotalCurrency());
		responseQuotation.setTotalFee(studyFeeProxy.getOutTotalIefSuppliedTotalCurrency());
		responseQuotation.setPaymentDue(studyFeeProxy.getOutRegPaymentIefSuppliedTotalCurrency());
		responseQuotation.setPrescribedBooks(studyFeeProxy.getOutWsPrescribedBooksAmount());

		return responseQuotation;
	}


	public StudyQuotationInfo calculateStudyQuotation(StudyQuotationRequestInfo request) throws OperationFailedException {
		try {
			// Get a reference to the proxy
			final Srrqn01sQuoteStudyFees studyFeeProxy = getProxyInstance();
			final AtomicReference<OperationFailedException> exceptionReference = new AtomicReference<>();
			final ActionListener exceptionListener = e -> exceptionReference.set(new OperationFailedException(e.getActionCommand()));

			studyFeeProxy.addExceptionListener(exceptionListener);

			// Set all the inputs on the proxy
			populateInputs(studyFeeProxy, request);

			// Execute the remote call
			studyFeeProxy.execute();

			// Check if there was an exception
			if (exceptionReference.get() != null) {
				throw exceptionReference.get();
			} else if (studyFeeProxy.getExitStateType() < 3) {
				throw new OperationFailedException(studyFeeProxy.getExitStateMsg());
			}

			// Get the response from the proxy and return the quotation
			return buildResponse(studyFeeProxy, request);
		}catch (PropertyVetoException e){
			throw new OperationFailedException(e);
		}
	}
}
