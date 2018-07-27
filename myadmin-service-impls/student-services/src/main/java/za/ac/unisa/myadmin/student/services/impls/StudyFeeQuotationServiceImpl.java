package za.ac.unisa.myadmin.student.services.impls;

import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import org.apache.commons.lang3.StringUtils;

import Srrqn01h.Abean.Srrqn01sQuoteStudyFees;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.common.services.CommonServicesConstants;
import za.ac.unisa.myadmin.fees.services.FeesServicesConstants;
import za.ac.unisa.myadmin.fees.services.StudyFeeQuotationService;
import za.ac.unisa.myadmin.fees.services.dto.StudyFeeQuotationInfo;
import za.ac.unisa.myadmin.fees.services.dto.StudyFeeQuotationRequestInfo;
import za.ac.unisa.myadmin.fees.services.dto.StudyUnitInfo;

public class StudyFeeQuotationServiceImpl implements StudyFeeQuotationService {

	private final String POSTGRAD = "99999";

	public StudyFeeQuotationInfo submitStudyFeeQuotationRequest(StudyFeeQuotationRequestInfo studyFeeQuotationRequest)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		// If both the qualification and qualification code is unset
		if ((POSTGRAD.equalsIgnoreCase(studyFeeQuotationRequest.getQualification()))
				&& (StringUtils.isBlank(studyFeeQuotationRequest.getQualificationCode()))) {
			throw new InvalidParameterException("Qualification code is required");
		}

		try {
			final AtomicReference<OperationFailedException> exceptionReference = new AtomicReference<>();
			final ActionListener exceptionListener = e -> exceptionReference
					.set(new OperationFailedException(e.getActionCommand()));

			// Get a reference to the proxy
			final Srrqn01sQuoteStudyFees studyFeeQuotationProxy = constructStudyFeeQuotationProxy();
			studyFeeQuotationProxy.addExceptionListener(exceptionListener);
			studyFeeQuotationProxy
					.setInStudentAnnualRecordMkAcademicYear(studyFeeQuotationRequest.getAcademicYear().shortValue());

			// If the qualification is unset, but the qualification code is set
			if ((POSTGRAD.equalsIgnoreCase(studyFeeQuotationRequest.getQualification()))
					&& (StringUtils.isNotBlank(studyFeeQuotationRequest.getQualificationCode()))) {
				studyFeeQuotationProxy
						.setInStudentAcademicRecordMkQualificationCode(studyFeeQuotationRequest.getQualificationCode());
			}
			// Else both qualification and qualification code is set
			else {
				studyFeeQuotationProxy
						.setInStudentAcademicRecordMkQualificationCode(studyFeeQuotationRequest.getQualificationCode());
			}

			studyFeeQuotationProxy.setInWsCountryCode(studyFeeQuotationRequest.getCountryCode());
			studyFeeQuotationProxy.setInSmartcardIefSuppliedFlag(studyFeeQuotationRequest.isLibraryCard() ? "Y" : "N");
			studyFeeQuotationProxy
					.setInMatrExemptionIefSuppliedFlag(studyFeeQuotationRequest.isMatricExemption() ? "Y" : "N");

			List<String> studyCodes = studyFeeQuotationRequest.getCourseCodes();
			for (int idx = 0; idx < studyCodes.size(); idx++) {
				String code = studyCodes.get(idx);
				studyFeeQuotationProxy.setInGStudentStudyUnitMkStudyUnitCode(idx + 1, code.toUpperCase());
			}

			// Execute the remote call
			studyFeeQuotationProxy.execute();

			// Check if there was an exception
			if (exceptionReference.get() != null) {
				throw exceptionReference.get();
			} else if (studyFeeQuotationProxy.getExitStateType() < 3) {
				throw new OperationFailedException(studyFeeQuotationProxy.getExitStateMsg());
			}

			// Get the response from the proxy and return the quotation
			return assembleStudentQuotationResponse(studyFeeQuotationProxy, studyFeeQuotationRequest);
		} catch (PropertyVetoException e) {
			throw new OperationFailedException(e);
		}
	}

	/**
	 * Get an instance of the StudyFee proxy
	 *
	 * @return
	 * @throws PropertyVetoException
	 */
	private Srrqn01sQuoteStudyFees constructStudyFeeQuotationProxy() throws PropertyVetoException {
		Srrqn01sQuoteStudyFees studyFeeQuotationProxy = new Srrqn01sQuoteStudyFees();
		studyFeeQuotationProxy.clear();
		studyFeeQuotationProxy.setInWsUserNumber(FeesServicesConstants.STUDY_QUOTE_FEE_PROXY_USER_NUMBER);
		studyFeeQuotationProxy.setInWsWorkstationCode(CommonServicesConstants.PROXY_WORKSTATION_CODE);
		studyFeeQuotationProxy.setInCsfClientServerCommunicationsClientVersionNumber(
				CommonServicesConstants.PROXY_CLIENT_SERVER_COMMUNICATIONS_CLIENT_VERSION);
		studyFeeQuotationProxy.setInCsfClientServerCommunicationsClientRevisionNumber(
				CommonServicesConstants.PROXY_CLIENT_SERVER_COMMUNICATIONS_CLIENT_REVISION);
		studyFeeQuotationProxy.setInCsfClientServerCommunicationsAction(
				FeesServicesConstants.STUDY_QUOTE_FEE_PROXY_CLIENT_SERVER_COMMUNICATIONS_ACTION);
		studyFeeQuotationProxy.setInCsfClientServerCommunicationsClientDevelopmentPhase(
				CommonServicesConstants.PROXY_CLIENT_SERVER_COMMUNICATIONS_CLIENT_DEVELOPMENT_PHASE);
		studyFeeQuotationProxy.setInStudentAnnualRecordMkStudentNr(
				FeesServicesConstants.STUDY_QUOTE_FEE_PROXY_ANNUAL_RECORD_MK_STUDENT_NBR);
		studyFeeQuotationProxy.setInWsStudentSurname(FeesServicesConstants.STUDY_QUOTE_FEE_PROXY_STUDENT_SURNAME);
		studyFeeQuotationProxy.setInWsStudentInitials(FeesServicesConstants.STUDY_QUOTE_FEE_PROXY_STUDENT_INITIALS);
		studyFeeQuotationProxy.setInWsStudentMkCorrespondenceLanguage(
				FeesServicesConstants.STUDY_QUOTE_FEE_PROXY_STUDENT_MK_CORRESPONDENCE_LANGUAGE);
		studyFeeQuotationProxy
				.setInWsAddressPostalCode(FeesServicesConstants.STUDY_QUOTE_FEE_PROXY_ADDRESS_POSTAL_CODE);
		return studyFeeQuotationProxy;
	}

	/**
	 * Build a study quotation using the input request, and the response from the
	 * proxy execution.
	 *
	 * @param studyFeeQuotationProxy
	 *            The <code>Srrqn01sQuoteStudyFees</code> proxy that was used to
	 *            execute the request
	 * @param studyFeeQuotationRequest
	 *            The <code>StudyQuotationRequestInfo</code> request used to request
	 *            the quotation.
	 * @return A <code>StudyQuotationInfo</code> object.
	 * @throws OperationFailedException
	 * @throws InvalidParameterException
	 */
	private StudyFeeQuotationInfo assembleStudentQuotationResponse(Srrqn01sQuoteStudyFees studyFeeQuotationProxy,
			StudyFeeQuotationRequestInfo studyFeeQuotationRequest)
			throws OperationFailedException, InvalidParameterException {
		final StudyFeeQuotationInfo responseQuotation = new StudyFeeQuotationInfo(studyFeeQuotationRequest);

		String message = studyFeeQuotationProxy.getOutCsfStringsString500();
		if (StringUtils.isNotBlank(message) && !"Error reading study unit cost information".equalsIgnoreCase(message)) {
			throw new InvalidParameterException(message);
		} else if (StringUtils.isNotBlank(message)) {
			responseQuotation.setMessage(message);
		}

		for (int i = 0; i <= (studyFeeQuotationProxy.getOutGroupCount() - 1); i++) {
			StudyUnitInfo studyUnitInfo = new StudyUnitInfo();
			studyUnitInfo.setCode(studyFeeQuotationProxy.getOutGInternetWsStudyUnitCode(i));
			studyUnitInfo.setDescription(studyFeeQuotationProxy.getOutGInternetWsStudyUnitEngShortDescription(i));
			studyUnitInfo.setFee(studyFeeQuotationProxy.getOutGStudyUnitCostIefSuppliedTotalCurrency(i));
			responseQuotation.getStudyUnits().add(studyUnitInfo);
		}

		responseQuotation.setForeignLevy(studyFeeQuotationProxy.getOutForeignLevyIefSuppliedTotalCurrency());
		responseQuotation.setLibraryCardCost(studyFeeQuotationProxy.getOutSmartcardIefSuppliedTotalCurrency());
		responseQuotation.setMatricExemptionCost(studyFeeQuotationProxy.getOutMatrExemptionIefSuppliedTotalCurrency());
		responseQuotation.setTotalFee(studyFeeQuotationProxy.getOutTotalIefSuppliedTotalCurrency());
		responseQuotation.setPaymentDue(studyFeeQuotationProxy.getOutRegPaymentIefSuppliedTotalCurrency());
		responseQuotation.setPrescribedBooks(studyFeeQuotationProxy.getOutWsPrescribedBooksAmount());

		return responseQuotation;
	}


}
