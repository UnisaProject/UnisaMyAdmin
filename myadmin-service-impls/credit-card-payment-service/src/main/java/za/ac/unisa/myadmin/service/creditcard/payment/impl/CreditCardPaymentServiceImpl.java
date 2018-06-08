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
import za.ac.unisa.myadmin.creditcard.payment.SummaryInfo;
import za.ac.unisa.myadmin.creditcard.payment.TpPaymentInfo;
import za.ac.unisa.myadmin.service.creditcard.payment.dao.StudentRepository;

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
	@Autowired
	private StudentRepository studentRepository;

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
			ccPaymentsProxy.setInCsfClientServerCommunicationsClientVersionNumber((short) 3);
			ccPaymentsProxy.setInCsfClientServerCommunicationsClientRevisionNumber((short) 1);
			ccPaymentsProxy.setInCsfClientServerCommunicationsAction("DS");
			ccPaymentsProxy.setInCsfClientServerCommunicationsClientDevelopmentPhase("C");
			ccPaymentsProxy.setInSecurityWsUserNumber(99998);
			ccPaymentsProxy.setInWsWorkstationCode("internet");
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
			} else {
				// set student info
				response.getStudentInfo().setStudentNumber(String.valueOf(ccPaymentsProxy.getOutWsStudentNr()));
				response.getStudentInfo().setStudentName(ccPaymentsProxy.getOutNameCsfStringsString40().trim());
				response.getQualificationInfo().setQualCode(ccPaymentsProxy.getOutWsQualificationCode());
				response.getQualificationInfo().setQualDesc(ccPaymentsProxy.getOutWsQualificationEngDescription().trim());
				response.getStudentInfo().setEmailAddress(ccPaymentsProxy.getOutWsAddressV2EmailAddress().trim());
				response.setRegStatus(ccPaymentsProxy.getOutWsStudentAnnualRecordStatusCode());
				if ("RG".equalsIgnoreCase(response.getRegStatus())) {
					response.setRegStatusDescription("Registered");
				} else if ("TN".equalsIgnoreCase(response.getRegStatus())) {
					response.setRegStatusDescription("Registration Pending");
				} else if ("CA".equalsIgnoreCase(response.getRegStatus())) {
					response.setRegStatusDescription("Registration cancelled");
				} else {
					response.setRegStatusDescription("Not Registered");
				}
				// set study fees for output in form
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
					//response.setLibraryFineBalance(response.getLibraryFineBalance().replace("-", ""));
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
				// more TP fees
				response.setFullAccount(new BigDecimal(ccPaymentsProxy.getOutDueImmediatelyIefSuppliedAverageCurrency()));
				response.setDueImmediately(new BigDecimal(ccPaymentsProxy.getOutDueStudentAccountDueImmediately()));
				response.setMinimumStudyFee(new BigDecimal(ccPaymentsProxy.getOutStudyDueIefSuppliedAverageCurrency()));
				response.setMinimumForReg(new BigDecimal(ccPaymentsProxy.getOutTempSfCalculatedStudyFeesMinimum()));
				// non-tp: if smartcard balance > 0, don't show
				if (ccPaymentsProxy.getOutWsSmartCardDataBalance() > 0) {
					response.setCanChooseLibraryCard(false);
				}
				// non-tp: if mr balance > min mr fee, don't show
				if (ccPaymentsProxy.getOutMrStudentAccountBalance() > 0 && ccPaymentsProxy.getOutMrStudentAccountBalance() >= ccPaymentsProxy.getOutMrDueIefSuppliedAverageCurrency()) {
					response.setCanChooseMatric(false);
				}
				// non-tp: if 3G DataBundle balance > min 3G DataBundle fee, dont show
				if (ccPaymentsProxy.getOut3gBundleDocumentTotalAmount() == 0) {
					response.setCanChooseThreeGDataBundle(false);
				}
				//student number application fee jul 2010
				response.setApplyAmount(new BigDecimal(ccPaymentsProxy.getOutSmartcardAndApplCostWsAcademicYearApplicationCost()));

				//------------------------------------------------------------------------------
				LOG.debug("CreditCardPayment: processStudentInput(); " + response.toString());
				//------------------------------------------------------------------------------
			}
			return response;
		} catch (PropertyVetoException e) {
			throw new OperationFailedException(e);
		}
	}

	@Override
	public String getSmartCardValue(Integer userId) throws OperationFailedException {
		return studentRepository.getSmartCardIssuedByStudentNumber(userId);
	}

	@Override
	public SummaryInfo processApplicationPayment(ApplicationPaymentInfo paymentInfo) throws OperationFailedException {
		//TODO
		//eventTrackingService = (EventTrackingService) ComponentManager.get(EventTrackingService.class);
		//toolManager = (ToolManager) ComponentManager.get(ToolManager.class);
		try {
			LOG.debug("CreditCardPayment: Start processApplicationPayment()");
			SummaryInfo response = new SummaryInfo();
			Sfrrf03sMntOnlineCcPayments ccPaymentsProxy = new Sfrrf03sMntOnlineCcPayments();
			final AtomicReference<OperationFailedException> exceptionReference = new AtomicReference<>();
			final ActionListener exceptionListener = e -> exceptionReference.set(new OperationFailedException(e.getActionCommand()));
			ccPaymentsProxy.addExceptionListener(exceptionListener);
			ccPaymentsProxy.clear();
			ccPaymentsProxy.setInCsfClientServerCommunicationsClientVersionNumber((short) 3);
			ccPaymentsProxy.setInCsfClientServerCommunicationsClientRevisionNumber((short) 1);
			ccPaymentsProxy.setInCsfClientServerCommunicationsAction("A");
			ccPaymentsProxy.setInCsfClientServerCommunicationsClientDevelopmentPhase("C");
			ccPaymentsProxy.setInSecurityWsUserNumber(99998);
			// student nr
			ccPaymentsProxy.setInWsStudentNr(Integer.parseInt(paymentInfo.getStudentInfo().getStudentNumber()));
			// email
			ccPaymentsProxy.setInWsAddressV2EmailAddress(paymentInfo.getStudentInfo().getEmailAddress());
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
			} else {
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

			}
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

			// validate input
			String errMsg = null; // = //validateNonTpPaymentInfo(creditForm, mapping, request, response);
			if (StringUtils.hasText(errMsg)) {
				throw new OperationFailedException(errMsg);
//				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.coolgenerror", errMsg));
//				addErrors(request, messages);
//				request.setAttribute("cnumber", creditForm.getCardNumber());
//				request.setAttribute("cvvnumber", creditForm.getCvvNo());
//				return nonTpPaymentInput(mapping, form, request, response);
			}

			// Process payment
			Sfrrf03sMntOnlineCcPayments ccPaymentsProxy = new Sfrrf03sMntOnlineCcPayments();
			final AtomicReference<OperationFailedException> exceptionReference = new AtomicReference<>();
			final ActionListener exceptionListener = e -> exceptionReference.set(new OperationFailedException(e.getActionCommand()));
			ccPaymentsProxy.addExceptionListener(exceptionListener);
			ccPaymentsProxy.clear();
			ccPaymentsProxy.setInCsfClientServerCommunicationsClientVersionNumber((short) 3);
			ccPaymentsProxy.setInCsfClientServerCommunicationsClientRevisionNumber((short) 1);
			ccPaymentsProxy.setInCsfClientServerCommunicationsAction("A");
			ccPaymentsProxy.setInCsfClientServerCommunicationsClientDevelopmentPhase("C");

			// user nr
			ccPaymentsProxy.setInSecurityWsUserNumber(99998);
			ccPaymentsProxy.setInWsWorkstationCode("internet");
			// student nr
			ccPaymentsProxy.setInWsStudentNr(Integer.parseInt(paymentInfo.getStudentInfo().getStudentNumber()));
			// qual
			ccPaymentsProxy.setInWsQualificationCode(paymentInfo.getQualificationInfo().getQualCode());
			// email
			ccPaymentsProxy.setInWsAddressV2EmailAddress(paymentInfo.getStudentInfo().getEmailAddress());
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
			} else {
				// Check error flag
				if ("Y".equalsIgnoreCase(ccPaymentsProxy.getOutRetryXtnIefSuppliedFlag())) {
					throw new OperationFailedException(ccPaymentsProxy.getOutCsfStringsString500());
					//messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.coolgenerror", errormsg));
					//addErrors(request, messages);
					//return "nonTpPayment";
				} else {
					response.setSummaryMessage(ccPaymentsProxy.getOutCsfStringsString500());
					if ("Y".equals(ccPaymentsProxy.getOutErrorIefSuppliedFlag())) {
						response.setErrorFlag(true);
					} else {
						response.setErrorFlag(false);
					}
					//clear form vars
					//reset(creditForm);
				}
			}
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
			if (!paymentInfo.isCancelSmartCard() && paymentInfo.isCanSmartCardCancel()) {
				paymentInfo.setCancelSmartCard(false);
				// reset to original amount
				//paymentInfo.setLibraryFeeForStudent(paymentInfo.getLibraryFeeAmount());
				// update database field
				this.updateSmartCardValue("W", Integer.valueOf(paymentInfo.getStudentInfo().getStudentNumber()));
			} else if (paymentInfo.isCancelSmartCard() && paymentInfo.isCanSmartCardCancel()) {
				paymentInfo.setCancelSmartCard(true);
				// update database field
				this.updateSmartCardValue("N", Integer.valueOf(paymentInfo.getStudentInfo().getStudentNumber()));
			}
			//------------------
			// validate input
			//------------------
			String errMsg = null; //validateTpPaymentInfo(paymentInfo, mapping, request, response);
			if (StringUtils.hasText(errMsg)) {
				throw new OperationFailedException(errMsg);
				//messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.coolgenerror", errMsg));
				//addErrors(request, messages);
				//request.setAttribute("cnumber", creditForm.getCardNumber());
				//request.setAttribute("cvvnumber", creditForm.getCvvNo());
				//return nonTpPaymentInput(mapping, form, request, response);
			}
			// Process payment
			Sfrrf03sMntOnlineCcPayments ccPaymentsProxy = new Sfrrf03sMntOnlineCcPayments();
			final AtomicReference<OperationFailedException> exceptionReference = new AtomicReference<>();
			final ActionListener exceptionListener = e -> exceptionReference.set(new OperationFailedException(e.getActionCommand()));
			ccPaymentsProxy.addExceptionListener(exceptionListener);
			ccPaymentsProxy.clear();

			ccPaymentsProxy.setInCsfClientServerCommunicationsClientVersionNumber((short) 3);
			ccPaymentsProxy.setInCsfClientServerCommunicationsClientRevisionNumber((short) 1);
			ccPaymentsProxy.setInCsfClientServerCommunicationsAction("A");
			ccPaymentsProxy.setInCsfClientServerCommunicationsClientDevelopmentPhase("C");
			// user nr
			ccPaymentsProxy.setInSecurityWsUserNumber(99998);
			ccPaymentsProxy.setInWsWorkstationCode("internet");
			// student nr
			ccPaymentsProxy.setInWsStudentNr(Integer.parseInt(paymentInfo.getStudentInfo().getStudentNumber()));
			// qual
			ccPaymentsProxy.setInWsQualificationCode(paymentInfo.getQualificationInfo().getQualCode());
			// email
			ccPaymentsProxy.setInWsAddressV2EmailAddress(paymentInfo.getStudentInfo().getEmailAddress());
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
			} else {

				// Check error flag
				if ("Y".equalsIgnoreCase(ccPaymentsProxy.getOutRetryXtnIefSuppliedFlag())) {
					throw new OperationFailedException(ccPaymentsProxy.getOutCsfStringsString500());
					//String errormsg = ccPaymentsProxy.getOutCsfStringsString500();
					//messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.coolgenerror", errormsg));
					//addErrors(request, messages);
					//return "tpPayment";
				} else {
					response.setSummaryMessage(ccPaymentsProxy.getOutCsfStringsString500());
					if ("Y".equals(ccPaymentsProxy.getOutErrorIefSuppliedFlag())) {
						response.setErrorFlag(true);
					} else {
						response.setErrorFlag(false);
					}
					//clear form vars
					//reset(creditForm);
				}
			}

			//eventTrackingService.post(eventTrackingService.newEvent(EventTrackingTypes.EVENT_CREDITCARD_PAYMENT, toolManager.getCurrentPlacement().getContext(), false));

			//--------------------------------------------------------------------------------
			LOG.debug("CreditCardPayment: processTpPayment() result for stud: " + paymentInfo.getStudentInfo().getStudentNumber() + "; " + response.getSummaryMessage());
			//--------------------------------------------------------------------------------


			return response;
		} catch (PropertyVetoException e) {
			throw new OperationFailedException(e);
		}
	}

	@Override
	@Transactional
	public int updateSmartCardValue(String smartCard, Integer studentNumber) {
		return studentRepository.updatesmartCardIssuedByStudentNumber(smartCard, studentNumber);
	}

	@Override
	public CreditCardPaymentInfo processQualInput(Integer studentNumber, String qualCode) throws OperationFailedException {

		if (studentNumber == null) {
			throw new OperationFailedException("Student Number required");
		}
		if (StringUtils.isEmpty(qualCode)) {
			throw new OperationFailedException("Qualification code required");
		}
		try {
			CreditCardPaymentInfo response = new CreditCardPaymentInfo();
			Sfrrf03sMntOnlineCcPayments ccPaymentsProxy = new Sfrrf03sMntOnlineCcPayments();
			final AtomicReference<OperationFailedException> exceptionReference = new AtomicReference<>();
			final ActionListener exceptionListener = e -> exceptionReference.set(new OperationFailedException(e.getActionCommand()));
			ccPaymentsProxy.addExceptionListener(exceptionListener);
			ccPaymentsProxy.clear();
		/* op.setTracing(Trace.MASK_ALL); */
			// op.setInIpAddressCsfStringsString15(request.getRemoteAddr());
			ccPaymentsProxy.setInCsfClientServerCommunicationsClientVersionNumber((short) 3);
			ccPaymentsProxy.setInCsfClientServerCommunicationsClientRevisionNumber((short) 1);
			ccPaymentsProxy.setInCsfClientServerCommunicationsAction("GD");
			ccPaymentsProxy.setInCsfClientServerCommunicationsClientDevelopmentPhase("C");
			ccPaymentsProxy.setInSecurityWsUserNumber(99998);
			ccPaymentsProxy.setInWsWorkstationCode("internet");
			ccPaymentsProxy.setInWsStudentNr(studentNumber);
			ccPaymentsProxy.setInWsQualificationCode(qualCode);

			ccPaymentsProxy.execute();
			if (exceptionReference.get() != null) {
				throw new OperationFailedException("Coolgen Error return to QualInput");
				//return false;
				//throw opl.getException();
			}

			if (ccPaymentsProxy.getExitStateType() < 3) {
				throw new OperationFailedException("Coolgen Error return to QualInput");
				//return false;
				//throw new Exception(ccPaymentsProxy.getExitStateMsg());
			}
			// Check error flag
			if ("Y".equalsIgnoreCase(ccPaymentsProxy.getOutErrorIefSuppliedFlag())) {

				throw new OperationFailedException(ccPaymentsProxy.getOutCsfStringsString500());
				//String errormsg = ccPaymentsProxy.getOutCsfStringsString500();
				//messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.coolgenerror", errormsg));
				//addErrors(request, messages);
				//creditForm.getQual().setQualDesc("");
				//return false;
			} else {
				response.getQualificationInfo().setQualCode(ccPaymentsProxy.getOutWsQualificationCode());
				response.getQualificationInfo().setQualDesc(ccPaymentsProxy.getOutWsQualificationEngDescription());
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
				// more TP fees
				response.setFullAccount(new BigDecimal(ccPaymentsProxy.getOutDueImmediatelyIefSuppliedAverageCurrency()));
				response.setDueImmediately(new BigDecimal(ccPaymentsProxy.getOutDueStudentAccountDueImmediately()));
				response.setMinimumStudyFee(new BigDecimal(ccPaymentsProxy.getOutStudyDueIefSuppliedAverageCurrency()));
				response.setMinimumForReg(new BigDecimal(ccPaymentsProxy.getOutTempSfCalculatedStudyFeesMinimum()));
			}

			return response;
		} catch (PropertyVetoException e) {
			throw new OperationFailedException(e);
		}
	}
}
