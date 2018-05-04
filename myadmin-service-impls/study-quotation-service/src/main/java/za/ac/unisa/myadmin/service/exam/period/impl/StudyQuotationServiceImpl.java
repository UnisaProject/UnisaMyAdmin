package za.ac.unisa.myadmin.service.exam.period.impl;

import Srrqn01h.Abean.Srrqn01sQuoteStudyFees;
import org.springframework.stereotype.Service;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.studyquotation.quotation.StudyQuotation;
import za.ac.unisa.myadmin.studyquotation.quotation.StudyQuotationRequest;
import za.ac.unisa.myadmin.studyquotation.quotation.StudyQuotationService;
import za.ac.unisa.myadmin.studyquotation.quotation.StudyUnit;

import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service("StudyQuotationService")
public class StudyQuotationServiceImpl implements StudyQuotationService {

	/**
	 * Get an instance of the StudyFee proxy
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
	 * Populate all the input parameters from the <code>StudyQuotationRequest</code> onto the proxy class
	 * @param studyFeeProxy The proxy to populate the inputs to
	 * @param request The quotation request to copy the inputs from.
	 * @throws PropertyVetoException
	 * @throws OperationFailedException
	 */
	private void populateInputs(Srrqn01sQuoteStudyFees studyFeeProxy, StudyQuotationRequest request) throws PropertyVetoException, OperationFailedException {
		studyFeeProxy.setInStudentAnnualRecordMkAcademicYear((short)request.getAcademicYear());

		if ((request.getQualification().equalsIgnoreCase("99999")) && (request.getQualificationCode().equalsIgnoreCase(""))) {
			throw new OperationFailedException("Qualification code is missing");
		} else if ((request.getQualification().equalsIgnoreCase("99999")) && (!request.getQualificationCode().equalsIgnoreCase(""))) {
			studyFeeProxy.setInStudentAcademicRecordMkQualificationCode(request.getQualificationCode());
			request.setQualification(request.getQualificationCode());
		} else {
			studyFeeProxy.setInStudentAcademicRecordMkQualificationCode(request.getQualification());
		}

		studyFeeProxy.setInWsCountryCode(request.getCountryCode());
		studyFeeProxy.setInSmartcardIefSuppliedFlag(request.isLibraryCard() ? "Y" : "N");
		studyFeeProxy.setInMatrExemptionIefSuppliedFlag(request.isMatricExemption() ? "Y" : "N");

		List<String> studyCodes = request.getStudyCodes();
		for(int idx = 0 ; idx < studyCodes.size(); idx++){
			String code = studyCodes.get(idx);
			studyFeeProxy.setInGStudentStudyUnitMkStudyUnitCode(idx+1, code.toUpperCase());
		}
	}

	/**
	 * Build a study quotation using the input request, and the response from the proxy execution.
	 * @param studyFeeProxy The <code>Srrqn01sQuoteStudyFees</code> proxy that was used to execute the request
	 * @param request The <code>StudyQuotationRequest</code> request used to request the quotation.
	 * @return A <code>StudyQuotation</code> object.
	 * @throws OperationFailedException
	 */
	private StudyQuotation buildResponse(Srrqn01sQuoteStudyFees studyFeeProxy, StudyQuotationRequest request) throws OperationFailedException {
		final StudyQuotation responseQuotation = new StudyQuotation(request);

		String errorMessage = studyFeeProxy.getOutCsfStringsString500();
		if (!"Error reading study unit cost information".equalsIgnoreCase(errorMessage)){
			throw new OperationFailedException("Error while trying to get study unit cost information. " + errorMessage);
		}

		for(int i=0; i < (studyFeeProxy.getOutGroupCount()-1) ; i++){
			StudyUnit studyUnit = new StudyUnit();
			studyUnit.setStudyUnitcode(studyFeeProxy.getOutGInternetWsStudyUnitCode(i));
			studyUnit.setDescription(studyFeeProxy.getOutGInternetWsStudyUnitEngShortDescription(i));
			studyUnit.setFee(studyFeeProxy.getOutGStudyUnitCostIefSuppliedTotalCurrency(i));
			responseQuotation.addStudyUnit(studyUnit);
		}
		responseQuotation.setForeignLevy(studyFeeProxy.getOutForeignLevyIefSuppliedTotalCurrency());
		responseQuotation.setLibraryCardCost(studyFeeProxy.getOutSmartcardIefSuppliedTotalCurrency());
		responseQuotation.setMatricExemptionCost(studyFeeProxy.getOutMatrExemptionIefSuppliedTotalCurrency());
		responseQuotation.setTotalFee(studyFeeProxy.getOutTotalIefSuppliedTotalCurrency());
		responseQuotation.setPaymentDue(studyFeeProxy.getOutRegPaymentIefSuppliedTotalCurrency());
		responseQuotation.setPrescribedBooks(studyFeeProxy.getOutWsPrescribedBooksAmount());

		return responseQuotation;
	}


	public StudyQuotation calculateStudyQuotation(StudyQuotationRequest request) throws OperationFailedException {
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
			if(exceptionReference.get() != null){
				throw exceptionReference.get();
			}

			// Get the response from the proxy and return the quotation
			return buildResponse(studyFeeProxy, request);
		}catch (PropertyVetoException e){
			throw new OperationFailedException(e);
		}
	}
}
