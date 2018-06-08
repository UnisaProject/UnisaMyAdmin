package za.ac.unisa.myadmin.service.creditcard.payment.impl;

import Sfrrf03h.Abean.Sfrrf03sMntOnlineCcPayments;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.creditcard.payment.ApplicationPaymentInfo;
import za.ac.unisa.myadmin.creditcard.payment.CreditCardPaymentInfo;
import za.ac.unisa.myadmin.creditcard.payment.CreditCardPaymentService;
import za.ac.unisa.myadmin.creditcard.payment.SummaryInfo;

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

	@Override
	public CreditCardPaymentInfo processStudentInput(Integer userId) throws OperationFailedException {
		try {
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
				response.setLibrayFineBalance(new BigDecimal(ccPaymentsProxy.getOutLdDueStudentAccountBalance()));
				if (response.getLibrayFineBalance().compareTo(BigDecimal.ZERO) != 0) {
					response.setLibCreditDebitIndicator(ccPaymentsProxy.getOutLdDtCrIndCsfStringsString6());
					// replace minus sign
					response.setLibrayFineBalance(response.getLibrayFineBalance().abs());
					//response.setLibrayFineBalance(response.getLibrayFineBalance().replace("-", ""));
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
			//throw new Exception(op.getExitStateMsg());
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

}