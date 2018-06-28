package za.ac.unisa.myadmin.service.creditcard.payment.impl;

import Sfrrf03h.Abean.Sfrrf03sMntOnlineCcPayments;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.creditcard.payment.ApplicationPaymentInfo;
import za.ac.unisa.myadmin.creditcard.payment.CreditCardPaymentInfo;
import za.ac.unisa.myadmin.creditcard.payment.CreditCardPaymentService;
import za.ac.unisa.myadmin.creditcard.payment.NonTpPaymentInfo;
import za.ac.unisa.myadmin.creditcard.payment.QualPaymentInfo;
import za.ac.unisa.myadmin.creditcard.payment.SummaryInfo;
import za.ac.unisa.myadmin.creditcard.payment.TpPaymentInfo;
import za.ac.unisa.myadmin.student.services.StudentServiceConstants;
import za.ac.unisa.myadmin.student.services.student.StudentService;

import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by Adrian on 2018-06-04.
 */
@Service("CreditCardPaymentService")
public class CreditCardPaymentServiceImpl implements CreditCardPaymentService {

	/**
	 * A reference to a logger.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(CreditCardPaymentServiceImpl.class);

	private final String REG_STATUS_CODE_REGISTERED = "RG";
	private final String REG_STATUS_DESCRIPTION_REGISTERED = "Registered";
	private final String REG_STATUS_CODE_PENDING = "TN";
	private final String REG_STATUS_DESCRIPTION_PENDING = "Registration Pending";
	private final String REG_STATUS_CODE_CANCELLED = "CA";
	private final String REG_STATUS_DESCRIPTION_CANCELLED = "Registration cancelled";

	private final String REG_STATUS_DESCRIPTION_UNREGISTERED = "Not Registered";

	@Autowired
	private StudentService studentService;

	@Override
	public CreditCardPaymentInfo processStudentInput(Integer userId) throws OperationFailedException {
		try {
			if (userId == null) {
				throw new OperationFailedException("Student Number required");
			}
			CreditCardPaymentInfo response = new CreditCardPaymentInfo();
			Sfrrf03sMntOnlineCcPayments ccPaymentsProxy = new Sfrrf03sMntOnlineCcPayments();
			final AtomicReference<OperationFailedException> exceptionReference = new AtomicReference<>();
			final ActionListener exceptionListener = e -> exceptionReference.set(new OperationFailedException(e.getActionCommand()));
			ccPaymentsProxy.addExceptionListener(exceptionListener);
			ccPaymentsProxy.clear();
			ccPaymentsProxy.setInCsfClientServerCommunicationsClientVersionNumber(StudentServiceConstants.PROXY_CLIENT_SERVER_COMMUNICATIONS_CLIENT_VERSION);
			ccPaymentsProxy.setInCsfClientServerCommunicationsClientRevisionNumber(StudentServiceConstants.PROXY_CLIENT_SERVER_COMMUNICATIONS_CLIENT_REVISION);
			ccPaymentsProxy.setInCsfClientServerCommunicationsAction(StudentServiceConstants.CREDIT_CARD_PAYMENT_STUDENT_PROXY_CLIENT_SERVER_COMMUNICATIONS_ACTION);
			ccPaymentsProxy.setInCsfClientServerCommunicationsClientDevelopmentPhase(StudentServiceConstants.PROXY_CLIENT_SERVER_COMMUNICATIONS_CLIENT_DEVELOPMENT_PHASE);
			ccPaymentsProxy.setInSecurityWsUserNumber(StudentServiceConstants.CREDIT_CARD_PAYMENT_PROXY_USER_NUMBER);
			ccPaymentsProxy.setInWsWorkstationCode(StudentServiceConstants.PROXY_WORKSTATION_CODE);
			ccPaymentsProxy.setInWsStudentNr(userId);

			ccPaymentsProxy.execute();
			if (exceptionReference.get() != null) {
				//return false;
				LOG.error("CreditCardPayment- Exception was thrown; " + exceptionReference.get());
				throw exceptionReference.get();
			}
			if (ccPaymentsProxy.getExitStateType() < 3) {
				LOG.error("CreditCardPayment- CoolGEN error; action: DS; " + response.toStringStudent());
				throw new OperationFailedException(ccPaymentsProxy.getExitStateMsg());
			}
			// Check error flag
			if ("Y".equalsIgnoreCase(ccPaymentsProxy.getOutErrorIefSuppliedFlag())) {
				String errormsg = ccPaymentsProxy.getOutCsfStringsString500();
				LOG.error("CoolGen Error : " + errormsg);
				throw new OperationFailedException(errormsg);
			}
			// set response info
			response = buildInitialInputResponse(ccPaymentsProxy);

			//------------------------------------------------------------------------------
			LOG.debug("CreditCardPayment: processStudentInput(); " + response.toStringStudent());
			//------------------------------------------------------------------------------

			return response;
		} catch (PropertyVetoException e) {
			throw new OperationFailedException(e);
		}
	}

	@Override
	public QualPaymentInfo processQualInput(Integer studentNumber, String qualCode) throws OperationFailedException {

		if (studentNumber == null) {
			throw new OperationFailedException("Student Number required");
		}
		if (StringUtils.isEmpty(qualCode)) {
			throw new OperationFailedException("Qualification code required");
		}
		try {
			QualPaymentInfo response = new QualPaymentInfo();
			Sfrrf03sMntOnlineCcPayments ccPaymentsProxy = new Sfrrf03sMntOnlineCcPayments();
			final AtomicReference<OperationFailedException> exceptionReference = new AtomicReference<>();
			final ActionListener exceptionListener = e -> exceptionReference.set(new OperationFailedException(e.getActionCommand()));
			ccPaymentsProxy.addExceptionListener(exceptionListener);
			ccPaymentsProxy.clear();
			ccPaymentsProxy.setInCsfClientServerCommunicationsClientVersionNumber(StudentServiceConstants.PROXY_CLIENT_SERVER_COMMUNICATIONS_CLIENT_VERSION);
			ccPaymentsProxy.setInCsfClientServerCommunicationsClientRevisionNumber(StudentServiceConstants.PROXY_CLIENT_SERVER_COMMUNICATIONS_CLIENT_REVISION);
			ccPaymentsProxy.setInCsfClientServerCommunicationsAction(StudentServiceConstants.CREDIT_CARD_PAYMENT_QUAL_PROXY_CLIENT_SERVER_COMMUNICATIONS_ACTION);
			ccPaymentsProxy.setInCsfClientServerCommunicationsClientDevelopmentPhase(StudentServiceConstants.PROXY_CLIENT_SERVER_COMMUNICATIONS_CLIENT_DEVELOPMENT_PHASE);
			ccPaymentsProxy.setInSecurityWsUserNumber(StudentServiceConstants.CREDIT_CARD_PAYMENT_PROXY_USER_NUMBER);
			ccPaymentsProxy.setInWsWorkstationCode(StudentServiceConstants.PROXY_WORKSTATION_CODE);
			ccPaymentsProxy.setInWsStudentNr(studentNumber);
			ccPaymentsProxy.setInWsQualificationCode(qualCode);

			ccPaymentsProxy.execute();
			if (exceptionReference.get() != null) {
				//return false;
				LOG.error("CreditCardPayment- Exception was thrown; " + exceptionReference.get());
				throw exceptionReference.get();
			}
			if (ccPaymentsProxy.getExitStateType() < 3) {
				LOG.error("CreditCardPayment- CoolGEN error; action: GD; " + response.toStringStudent());
				throw new OperationFailedException(ccPaymentsProxy.getExitStateMsg());
			}
			// Check error flag
			if ("Y".equalsIgnoreCase(ccPaymentsProxy.getOutErrorIefSuppliedFlag())) {
				String errormsg = ccPaymentsProxy.getOutCsfStringsString500();
				LOG.error("CoolGen Error : " + errormsg);
				throw new OperationFailedException(errormsg);
			}
			// set response info
			response = buildQualInputUpdateResponse(ccPaymentsProxy);

			//------------------------------------------------------------------------------
			LOG.debug("CreditCardPayment: processQualInput(); " + response.toStringStudent());
			//------------------------------------------------------------------------------
			return response;
		} catch (PropertyVetoException e) {
			throw new OperationFailedException(e);
		}
	}

	@Override
	public SummaryInfo processApplicationPayment(ApplicationPaymentInfo paymentInfo) throws OperationFailedException {
		//TODO
		//eventTrackingService = (EventTrackingService) ComponentManager.get(EventTrackingService.class);
		//toolManager = (ToolManager) ComponentManager.get(ToolManager.class);
		try {
			LOG.debug("CreditCardPayment: Start processApplicationPayment()");
			SummaryInfo response = new SummaryInfo();
			final Sfrrf03sMntOnlineCcPayments ccPaymentsProxy = getPaymentProxyInstance();
			final AtomicReference<OperationFailedException> exceptionReference = new AtomicReference<>();
			final ActionListener exceptionListener = e -> exceptionReference.set(new OperationFailedException(e.getActionCommand()));
			ccPaymentsProxy.addExceptionListener(exceptionListener);

			// student nr
			ccPaymentsProxy.setInWsStudentNr(paymentInfo.getStudentInfo().getStudentNumber());
			// email
			//ccPaymentsProxy.setInWsAddressV2EmailAddress(paymentInfo.getStudentInfo().getEmailAddress());
			ccPaymentsProxy.setInWsAddressV2EmailAddress("adrian@opencollab.co.za");
			// applyForAmount
			//
			// credit card nr
			ccPaymentsProxy.setInBundleDocumentAccountNr(paymentInfo.getCardInfo().getCardNumber());
			// cvv nr
			ccPaymentsProxy.setInCvvWsPostilionTranStartCvvNr(paymentInfo.getCardInfo().getCvvNo());
			// card expiry date
			ccPaymentsProxy.setInBundleDocumentCreditCardExpiryDate(Integer.parseInt(paymentInfo.getCardInfo().getExpiryYear() + paymentInfo.getCardInfo().getExpiryMonth()));
			// budget period
			ccPaymentsProxy.setInBundleDocumentCreditCardBudgetPeriod(Short.parseShort(paymentInfo.getCardInfo().getBudgetPeriod()));
			// card holder
			ccPaymentsProxy.setInBundleDocumentAccountHolder(paymentInfo.getCardInfo().getCardHolder());
			//total card amount
			ccPaymentsProxy.setInBundleDocumentAmountCreditCard(paymentInfo.getCreditCardTotalAmountInput().doubleValue());
			ccPaymentsProxy.setInApplicationBundleDocumentTotalAmount(paymentInfo.getApplyAmountInput().doubleValue());

			//--------------------------------------------------------------------------------
			LOG.debug("CreditCardPayment: processApplyPayment(); " + paymentInfo.toString());
			//--------------------------------------------------------------------------------

			ccPaymentsProxy.execute();
			boolean exceptionHappened = false;
			if (exceptionReference.get() != null)
				exceptionHappened = true;
			//throw opl.getException();
			if (ccPaymentsProxy.getExitStateType() < 3)
				exceptionHappened = true;
			//throw new Exception(ccPaymentsProxy.getExitStateMsg());
			if (exceptionHappened) {
				String errmsg = "Technical Error happened, payment not processed";
				throw new OperationFailedException(errmsg);
				//creditForm.setSummaryMessage(errmsg);
				//creditForm.setErrorOccured(true);
			}
			response = buildPaymentResponse(ccPaymentsProxy);

			//eventTrackingService.post(eventTrackingService.newEvent(EventTrackingTypes.EVENT_CREDITCARD_PAYMENT, toolManager.getCurrentPlacement().getContext(), false));
			//--------------------------------------------------------------------------------
			LOG.debug("CreditCardPayment: processApplyPayment() result for stud: " + paymentInfo.getStudentInfo().getStudentNumber() + "; " + response.getSummaryMessage());
			//--------------------------------------------------------------------------------

			return response;
		} catch (PropertyVetoException e) {
			throw new OperationFailedException(e);
		}
	}

	@Override
	public SummaryInfo processNonTpPayment(NonTpPaymentInfo paymentInfo) throws OperationFailedException {
		try {
			LOG.debug("CreditCardPayment: processNonTpPayment()");
			SummaryInfo response = new SummaryInfo();
			// Process payment
			final Sfrrf03sMntOnlineCcPayments ccPaymentsProxy = getPaymentProxyInstance();
			final AtomicReference<OperationFailedException> exceptionReference = new AtomicReference<>();
			final ActionListener exceptionListener = e -> exceptionReference.set(new OperationFailedException(e.getActionCommand()));
			ccPaymentsProxy.addExceptionListener(exceptionListener);
			ccPaymentsProxy.setInWsWorkstationCode(StudentServiceConstants.PROXY_WORKSTATION_CODE);
			// student nr
			ccPaymentsProxy.setInWsStudentNr(paymentInfo.getStudentInfo().getStudentNumber());
			// qual
			ccPaymentsProxy.setInWsQualificationCode(paymentInfo.getQualificationInfo().getQualCode());
			// email
			//ccPaymentsProxy.setInWsAddressV2EmailAddress(paymentInfo.getStudentInfo().getEmailAddress());
			ccPaymentsProxy.setInWsAddressV2EmailAddress("adrian@opencollab.co.za");
			// lib card
			if (paymentInfo.isPayLibraryFee()) {
				ccPaymentsProxy.setInSmarctcardBundleDocumentTotalAmount(paymentInfo.getLibraryFee().doubleValue());
			}
			// MR fee
			if (paymentInfo.isPayMatricFirstAppFee()) {
				ccPaymentsProxy.setInMrBundleDocumentTotalAmount(paymentInfo.getMatricFirstAppFee().doubleValue());
			}
			//3G data bundle
			if (paymentInfo.isPayThreeGDataBundleFee()) {
				ccPaymentsProxy.setIn3gBundleDocumentTotalAmount(paymentInfo.getThreeGDataBundleFee().doubleValue());
			}
			// Study fee
			ccPaymentsProxy.setInStudyFeeBundleDocumentTotalAmount(paymentInfo.getStudyFeeAmount() == null ? 0 : paymentInfo.getStudyFeeAmount().doubleValue());
			//lib fine
			ccPaymentsProxy.setInLdBundleDocumentTotalAmount(paymentInfo.getLibraryFineFee() == null ? 0 : paymentInfo.getLibraryFineFee().doubleValue());
			// credit card nr
			ccPaymentsProxy.setInBundleDocumentAccountNr(paymentInfo.getCreditCardInfo().getCardNumber());
			// cvv nr
			ccPaymentsProxy.setInCvvWsPostilionTranStartCvvNr(paymentInfo.getCreditCardInfo().getCvvNo());
			// card expiry date
			ccPaymentsProxy.setInBundleDocumentCreditCardExpiryDate(Integer.parseInt(paymentInfo.getCreditCardInfo().getExpiryYear() + paymentInfo.getCreditCardInfo().getExpiryMonth()));
			// budget period
			ccPaymentsProxy.setInBundleDocumentCreditCardBudgetPeriod(Short.parseShort(paymentInfo.getCreditCardInfo().getBudgetPeriod()));
			// card holder
			ccPaymentsProxy.setInBundleDocumentAccountHolder(paymentInfo.getCreditCardInfo().getCardHolder());
			//total card amount
			ccPaymentsProxy.setInBundleDocumentAmountCreditCard(paymentInfo.getCreditCardTotalAmountInput() == null ? 0 : paymentInfo.getCreditCardTotalAmountInput().doubleValue());

			//--------------------------------------------------------------------------------
			//LOG.debug("CreditCardPayment: processNonTpPayment(); " + paymentInfo.toStringStudent() + paymentInfo.toStringFees());
			//--------------------------------------------------------------------------------

			ccPaymentsProxy.execute();

			boolean exceptionHappened = false;
			if (exceptionReference.get() != null)
				exceptionHappened = true;
			//throw opl.getException();
			if (ccPaymentsProxy.getExitStateType() < 3)
				exceptionHappened = true;
			//throw new Exception(ccPaymentsProxy.getExitStateMsg());
			if (exceptionHappened) {
				String errmsg = "Technical Error happened, check the data you entered and try again";
				throw new OperationFailedException(errmsg);
				//messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.coolgenerror", errmsg));
				//addErrors(request, messages);
				//return "nonTpPayment";
			}
			response = buildPaymentResponse(ccPaymentsProxy);
//
//			eventTrackingService.post(
//				eventTrackingService.newEvent(EventTrackingTypes.EVENT_CREDITCARD_PAYMENT, toolManager.getCurrentPlacement().getContext(), false));
			//--------------------------------------------------------------------------------
			LOG.debug("CreditCardPayment: processNonTpPayment() result for stud: " + paymentInfo.getStudentInfo().getStudentNumber() + "; " + response.getSummaryMessage());
			//--------------------------------------------------------------------------------

			return response;
		} catch (PropertyVetoException e) {
			throw new OperationFailedException(e);
		}
	}

	@Override
	public SummaryInfo processTpPayment(TpPaymentInfo paymentInfo) throws OperationFailedException {
		try {
			LOG.debug("CreditCardPayment: processTpPayment()");
			SummaryInfo response = new SummaryInfo();
			//
			// student indicated that he/she wants to cancel the library fee
			if (paymentInfo.getLibraryFeeForStudent().compareTo(BigDecimal.ZERO) > 0) {
				studentService.updateSmartCardValue("W", Integer.valueOf(paymentInfo.getStudentInfo().getStudentNumber()));
			}
			// Process payment
			final Sfrrf03sMntOnlineCcPayments ccPaymentsProxy = getPaymentProxyInstance();
			final AtomicReference<OperationFailedException> exceptionReference = new AtomicReference<>();
			final ActionListener exceptionListener = e -> exceptionReference.set(new OperationFailedException(e.getActionCommand()));
			ccPaymentsProxy.addExceptionListener(exceptionListener);
			ccPaymentsProxy.setInWsWorkstationCode(StudentServiceConstants.PROXY_WORKSTATION_CODE);
			// student nr
			ccPaymentsProxy.setInWsStudentNr(paymentInfo.getStudentInfo().getStudentNumber());
			// qual
			ccPaymentsProxy.setInWsQualificationCode(paymentInfo.getQualificationInfo().getQualCode());
			// email
			//ccPaymentsProxy.setInWsAddressV2EmailAddress(paymentInfo.getStudentInfo().getEmailAddress());
			ccPaymentsProxy.setInWsAddressV2EmailAddress("adrian@opencollab.co.za");
			// lib card
			ccPaymentsProxy.setInSmarctcardBundleDocumentTotalAmount(paymentInfo.getLibraryFeeForStudent() == null ? 0 : paymentInfo.getLibraryFeeForStudent().doubleValue());
			// MR fee
			ccPaymentsProxy.setInMrBundleDocumentTotalAmount(paymentInfo.getMatricFeeForStudent() == null ? 0 : paymentInfo.getMatricFeeForStudent().doubleValue());
			//lib fine
			ccPaymentsProxy.setInLdBundleDocumentTotalAmount(paymentInfo.getLibraryFineFeeForStudent() == null ? 0 : paymentInfo.getLibraryFineFeeForStudent().doubleValue());
			// 3G fee
			//ccPaymentsProxy.setIn3gBundleDocumentTotalAmount(creditForm.getThreeGDataBundleFeeForStudent());
			// Study fee
			ccPaymentsProxy.setInStudyFeeBundleDocumentTotalAmount(paymentInfo.getStudyFeeAmount() == null ? 0 : paymentInfo.getStudyFeeAmount().doubleValue());
			// credit card nr
			ccPaymentsProxy.setInBundleDocumentAccountNr(paymentInfo.getCreditCardInfo().getCardNumber());
			// cvv nr
			ccPaymentsProxy.setInCvvWsPostilionTranStartCvvNr(paymentInfo.getCreditCardInfo().getCvvNo());
			// card expiry date
			ccPaymentsProxy.setInBundleDocumentCreditCardExpiryDate(Integer.parseInt(paymentInfo.getCreditCardInfo().getExpiryYear() + paymentInfo.getCreditCardInfo().getExpiryMonth()));
			// budget period
			ccPaymentsProxy.setInBundleDocumentCreditCardBudgetPeriod(Short.parseShort(paymentInfo.getCreditCardInfo().getBudgetPeriod()));
			// card holder
			ccPaymentsProxy.setInBundleDocumentAccountHolder(paymentInfo.getCreditCardInfo().getCardHolder());
			//total card amount
			ccPaymentsProxy.setInBundleDocumentAmountCreditCard(paymentInfo.getCreditCardTotalAmountInput() == null ? 0 : paymentInfo.getCreditCardTotalAmountInput().doubleValue());

			//--------------------------------------------------------------------------------
			//LOG.debug("CreditCardPayment: processTpPayment(); " + paymentInfo.toString() + creditForm.toStringFees());
			//--------------------------------------------------------------------------------

			ccPaymentsProxy.execute();
			boolean exceptionHappened = false;
			if (exceptionReference.get() != null)
				exceptionHappened = true;
			//throw opl.getException();
			if (ccPaymentsProxy.getExitStateType() < 3)
				exceptionHappened = true;
			//throw new Exception(ccPaymentsProxy.getExitStateMsg());
			if (exceptionHappened) {
				String errmsg = "Technical Error happened, check the data you entered and try again";
				throw new OperationFailedException(errmsg);
				//messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.coolgenerror", errmsg));
				//addErrors(request, messages);
				//return "tpPayment";
			}

			response = buildPaymentResponse(ccPaymentsProxy);

			//eventTrackingService.post(eventTrackingService.newEvent(EventTrackingTypes.EVENT_CREDITCARD_PAYMENT, toolManager.getCurrentPlacement().getContext(), false));

			//--------------------------------------------------------------------------------
			LOG.debug("CreditCardPayment: processTpPayment() result for stud: " + paymentInfo.getStudentInfo().getStudentNumber() + "; " + response.getSummaryMessage());
			//--------------------------------------------------------------------------------

			return response;
		} catch (PropertyVetoException e) {
			throw new OperationFailedException(e);
		}
	}


	private SummaryInfo buildPaymentResponse(Sfrrf03sMntOnlineCcPayments ccPaymentsProxy) throws OperationFailedException {
		final SummaryInfo response = new SummaryInfo();
		// Check error flag
		if ("Y".equalsIgnoreCase(ccPaymentsProxy.getOutRetryXtnIefSuppliedFlag())) {
			String errormsg = ccPaymentsProxy.getOutCsfStringsString500();
			throw new OperationFailedException(errormsg);
			//messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.coolgenerror", errormsg));
			//addErrors(request, messages);
			//return "applyPayment";
		} else {
			response.setSummaryMessage(ccPaymentsProxy.getOutCsfStringsString500());
			if ("Y".equals(ccPaymentsProxy.getOutErrorIefSuppliedFlag())) {
				response.setErrorFlag(true);
			} else {
				response.setErrorFlag(false);
			}
			// clear form vars
			//reset(creditForm);
		}
		return response;
	}

	private QualPaymentInfo buildQualInputUpdateResponse(Sfrrf03sMntOnlineCcPayments ccPaymentsProxy) {
		final QualPaymentInfo response = new QualPaymentInfo();
		// set student info
		response.getStudentInfo().setStudentNumber(ccPaymentsProxy.getOutWsStudentNr());
		response.getStudentInfo().setStudentName(ccPaymentsProxy.getOutNameCsfStringsString40().trim());
		response.getStudentInfo().setEmailAddress(ccPaymentsProxy.getOutWsAddressV2EmailAddress().trim());
		response.getQualificationInfo().setQualCode(ccPaymentsProxy.getOutWsQualificationCode());
		response.getQualificationInfo().setQualDesc(ccPaymentsProxy.getOutWsQualificationEngDescription().trim());
		// set fees
		response.setBalance(new BigDecimal(ccPaymentsProxy.getOutDueStudentAccountBalance()));
		if (response.getBalance().compareTo(BigDecimal.ZERO) != 0) {
			response.setCreditDebitIndicator(ccPaymentsProxy.getOutDtCrIndCsfStringsString6());
			// replace minus sign
			response.setBalance(response.getBalance().abs());
		}
		response.setLibraryFineBalance(new BigDecimal(ccPaymentsProxy.getOutLdDueStudentAccountBalance()));
		if (response.getLibraryFineBalance().compareTo(BigDecimal.ZERO) != 0) {
			response.setLibCreditDebitIndicator(ccPaymentsProxy.getOutLdDtCrIndCsfStringsString6());
			// replace minus sign
			response.setLibraryFineBalance(response.getLibraryFineBalance().abs());
		}
		//
		response.setFullAccount(new BigDecimal(ccPaymentsProxy.getOutDueImmediatelyIefSuppliedAverageCurrency()));
		response.setDueImmediately(new BigDecimal(ccPaymentsProxy.getOutDueStudentAccountDueImmediately()));
		response.setMinimumStudyFee(new BigDecimal(ccPaymentsProxy.getOutStudyDueIefSuppliedAverageCurrency()));
		response.setMinimumForReg(new BigDecimal(ccPaymentsProxy.getOutTempSfCalculatedStudyFeesMinimum()));
		return response;
	}

	private CreditCardPaymentInfo buildInitialInputResponse(Sfrrf03sMntOnlineCcPayments ccPaymentsProxy) {
		final CreditCardPaymentInfo response = new CreditCardPaymentInfo();
		// set student info
		response.getStudentInfo().setStudentNumber(ccPaymentsProxy.getOutWsStudentNr());
		response.getStudentInfo().setStudentName(ccPaymentsProxy.getOutNameCsfStringsString40().trim());
		response.getQualificationInfo().setQualCode(ccPaymentsProxy.getOutWsQualificationCode());
		response.getQualificationInfo().setQualDesc(ccPaymentsProxy.getOutWsQualificationEngDescription().trim());
		response.getStudentInfo().setEmailAddress(ccPaymentsProxy.getOutWsAddressV2EmailAddress().trim());
		response.setRegStatus(ccPaymentsProxy.getOutWsStudentAnnualRecordStatusCode());
		if (REG_STATUS_CODE_REGISTERED.equalsIgnoreCase(response.getRegStatus())) {
			response.setRegStatusDescription(REG_STATUS_DESCRIPTION_REGISTERED);
		} else if (REG_STATUS_CODE_PENDING.equalsIgnoreCase(response.getRegStatus())) {
			response.setRegStatusDescription(REG_STATUS_DESCRIPTION_PENDING);
		} else if (REG_STATUS_CODE_CANCELLED.equalsIgnoreCase(response.getRegStatus())) {
			response.setRegStatusDescription(REG_STATUS_DESCRIPTION_CANCELLED);
		} else {
			response.setRegStatusDescription(REG_STATUS_DESCRIPTION_UNREGISTERED);
		}
		// set fees
		response.setBalance(new BigDecimal(ccPaymentsProxy.getOutDueStudentAccountBalance()));
		if (response.getBalance().compareTo(BigDecimal.ZERO) != 0) {
			response.setCreditDebitIndicator(ccPaymentsProxy.getOutDtCrIndCsfStringsString6());
			// replace minus sign
			response.setBalance(response.getBalance().abs());
		}
		response.setLibraryFineBalance(new BigDecimal(ccPaymentsProxy.getOutLdDueStudentAccountBalance()));
		if (response.getLibraryFineBalance().compareTo(BigDecimal.ZERO) != 0) {
			response.setLibCreditDebitIndicator(ccPaymentsProxy.getOutLdDtCrIndCsfStringsString6());
			// replace minus sign
			response.setLibraryFineBalance(response.getLibraryFineBalance().abs());
		}
		// set NON-TP matric +lib fees
		response.setLibraryFee(new BigDecimal(ccPaymentsProxy.getOutSmartcardAndApplCostWsAcademicYearSmartcardCost()));
		response.setLibraryFineFee(new BigDecimal(ccPaymentsProxy.getOutLdDueStudentAccountBalance()));
		response.setThreeGDataBundleFee(new BigDecimal(ccPaymentsProxy.getOut3gBundleDocumentTotalAmount()));
		response.setMatricFirstAppFee(new BigDecimal(ccPaymentsProxy.getOutMrFirstApplicationCostMrFlagAmount()));
		// set TP matric and lib fees as calculated by server
		response.setLibraryFeeForStudent(new BigDecimal(ccPaymentsProxy.getOutSmartcardAndApplDueWsAcademicYearSmartcardCost()));
		response.setLibraryFineFeeForStudent(new BigDecimal(ccPaymentsProxy.getOutLdDueStudentAccountBalance()));
		response.setThreeGDataBundleFeeForStudent(new BigDecimal(ccPaymentsProxy.getIn3gBundleDocumentTotalAmount()));
		response.setMatricFeeForStudent(new BigDecimal(ccPaymentsProxy.getOutMrDueIefSuppliedAverageCurrency()));
		//
		response.setFullAccount(new BigDecimal(ccPaymentsProxy.getOutDueImmediatelyIefSuppliedAverageCurrency()));
		response.setDueImmediately(new BigDecimal(ccPaymentsProxy.getOutDueStudentAccountDueImmediately()));
		response.setMinimumStudyFee(new BigDecimal(ccPaymentsProxy.getOutStudyDueIefSuppliedAverageCurrency()));
		response.setMinimumForReg(new BigDecimal(ccPaymentsProxy.getOutTempSfCalculatedStudyFeesMinimum()));
		// non-tp: if smartcard balance > 0, don't show
		if (ccPaymentsProxy.getOutWsSmartCardDataBalance() > 0) {
			response.setCanChooseLibraryCard(false);
		} else {
			response.setCanChooseLibraryCard(true);
		}
		// non-tp: if mr balance > min mr fee, don't show
		if (ccPaymentsProxy.getOutMrStudentAccountBalance() > 0 && ccPaymentsProxy.getOutMrStudentAccountBalance() >= ccPaymentsProxy.getOutMrDueIefSuppliedAverageCurrency()) {
			response.setCanChooseMatric(false);
		} else {
			response.setCanChooseMatric(true);
		}
		// non-tp: if 3G DataBundle balance > min 3G DataBundle fee, dont show
		if (ccPaymentsProxy.getOut3gBundleDocumentTotalAmount() == 0) {
			response.setCanChooseThreeGDataBundle(false);
		} else {
			response.setCanChooseThreeGDataBundle(true);
		}
		//student number application fee jul 2010
		response.setApplyAmount(new BigDecimal(ccPaymentsProxy.getOutSmartcardAndApplCostWsAcademicYearApplicationCost()));

		return response;
	}

	private Sfrrf03sMntOnlineCcPayments getPaymentProxyInstance() throws PropertyVetoException {
		Sfrrf03sMntOnlineCcPayments ccPaymentsProxy = new Sfrrf03sMntOnlineCcPayments();
		ccPaymentsProxy.clear();
		ccPaymentsProxy.setInCsfClientServerCommunicationsClientVersionNumber(StudentServiceConstants.PROXY_CLIENT_SERVER_COMMUNICATIONS_CLIENT_VERSION);
		ccPaymentsProxy.setInCsfClientServerCommunicationsClientRevisionNumber(StudentServiceConstants.PROXY_CLIENT_SERVER_COMMUNICATIONS_CLIENT_REVISION);
		ccPaymentsProxy.setInCsfClientServerCommunicationsAction(StudentServiceConstants.CREDIT_CARD_PAYMENT_PAYMENT_PROXY_CLIENT_SERVER_COMMUNICATIONS_ACTION);
		ccPaymentsProxy.setInCsfClientServerCommunicationsClientDevelopmentPhase(StudentServiceConstants.PROXY_CLIENT_SERVER_COMMUNICATIONS_CLIENT_DEVELOPMENT_PHASE);
		ccPaymentsProxy.setInSecurityWsUserNumber(StudentServiceConstants.CREDIT_CARD_PAYMENT_PROXY_USER_NUMBER);
		return ccPaymentsProxy;
	}
}
