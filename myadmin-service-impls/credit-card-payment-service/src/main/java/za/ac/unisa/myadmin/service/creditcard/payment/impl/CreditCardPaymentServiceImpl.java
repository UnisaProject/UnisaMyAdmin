package za.ac.unisa.myadmin.service.creditcard.payment.impl;

import Sfrrf03h.Abean.Sfrrf03sMntOnlineCcPayments;
import org.springframework.stereotype.Service;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.creditcard.payment.CreditCardPaymentInfo;
import za.ac.unisa.myadmin.creditcard.payment.CreditCardPaymentService;

import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by Adrian on 2018-06-04.
 */
@Service("CreditCardPaymentService")
public class CreditCardPaymentServiceImpl implements CreditCardPaymentService {
	@Override
	public CreditCardPaymentInfo processStudentInput(Integer userId) throws OperationFailedException, PropertyVetoException {
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
			//log.error("CreditCardPayment- Exception was thrown; "+ opl.getException());
			//throw opl.getException();
		}
		if (ccPaymentsProxy.getExitStateType() < 3) {
			//log.error("CreditCardPayment- CoolGEN error; action: DS; "+ creditForm.toStringStudent());
			//return false;
			//throw new Exception(ccPaymentsProxy.getExitStateMsg());
		}
		// Check error flag
		if ("Y".equalsIgnoreCase(ccPaymentsProxy.getOutErrorIefSuppliedFlag())) {
			String errormsg = ccPaymentsProxy.getOutCsfStringsString500();
			//messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.coolgenerror", errormsg));
			//addErrors(request, messages);
			//return false;
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
			// set study fees  for output in form
			NumberFormat formatter = new DecimalFormat("#0.00");
			response.setBalance(new BigDecimal(ccPaymentsProxy.getOutDueStudentAccountBalance()));
			//response.setBalance(formatter.format(ccPaymentsProxy.getOutDueStudentAccountBalance()));
			if (!"0.00".equals(response.getBalance())) {
				response.setCreditDebitIndicator(ccPaymentsProxy.getOutDtCrIndCsfStringsString6());
				// replace minus sign
				response.setBalance(response.getBalance().abs());
				//response.setBalance(response.getBalance().replace("-", ""));
			}
			response.setLibrayFineBalance(new BigDecimal(ccPaymentsProxy.getOutLdDueStudentAccountBalance()));
			//response.setLibrayFineBalance(formatter.format(ccPaymentsProxy.getOutLdDueStudentAccountBalance()));
			if (!"0.00".equals(response.getLibrayFineBalance())) {
				response.setLibCreditDebitIndicator(ccPaymentsProxy.getOutLdDtCrIndCsfStringsString6());
				// replace minus sign
				response.setLibrayFineBalance(response.getLibrayFineBalance().abs());
				//response.setLibrayFineBalance(response.getLibrayFineBalance().replace("-", ""));
			}
			// set NON-TP matric +lib fees
			//response.setLibraryFeeText("R"+Double.toString(ccPaymentsProxy.getOutSmartcardAndApplCostWsAcademicYearSmartcardCost())+"0");
			response.setLibraryFee(new BigDecimal(ccPaymentsProxy.getOutSmartcardAndApplCostWsAcademicYearSmartcardCost()));
			//response.setLibraryFineFeeText("R"+Double.toString(ccPaymentsProxy.getOutLdDueStudentAccountBalance())+"0");
			response.setLibraryFineFee(new BigDecimal(ccPaymentsProxy.getOutLdDueStudentAccountBalance()));
			//response.setThreeGDataBundleFeeText("R"+Double.toString(ccPaymentsProxy.getOut3gBundleDocumentTotalAmount())+"0");
			response.setThreeGDataBundleFee(new BigDecimal(ccPaymentsProxy.getOut3gBundleDocumentTotalAmount()));
			//response.setMatricFirstAppFeeText("R"+ccPaymentsProxy.getOutMrFirstApplicationCostMrFlagAmount() + "0");
			response.setMatricFirstAppFee(new BigDecimal(ccPaymentsProxy.getOutMrFirstApplicationCostMrFlagAmount()));
			// set TP matric and lib fees as calculated by server
			response.setLibraryFeeForStudent(new BigDecimal(ccPaymentsProxy.getOutSmartcardAndApplDueWsAcademicYearSmartcardCost()));
			response.setLibraryFeeAmount(new BigDecimal(ccPaymentsProxy.getOutSmartcardAndApplDueWsAcademicYearSmartcardCost()));
			response.setLibraryFineFeeAmount(new BigDecimal(ccPaymentsProxy.getOutLdDueStudentAccountBalance()));
			//response.setFormattedLibraryFeeForStudent(formatter.format(ccPaymentsProxy.getOutSmartcardAndApplDueWsAcademicYearSmartcardCost()));
			//creditForm.setThreeGDataBundleForStudent(ccPaymentsProxy.getOut3gBundleDocumentTotalAmount());//
			//creditForm.setThreeGDataBundleFeeAmount(ccPaymentsProxy.getOut3gBundleDocumentTotalAmount());
			//response.setFormattedThreeGDataBundleFeeForStudent(formatter.format(ccPaymentsProxy.getIn3gBundleDocumentTotalAmount()));
			response.setMatricFeeForStudent(new BigDecimal(ccPaymentsProxy.getOutMrDueIefSuppliedAverageCurrency()));
			//response.setFormattedMatricFeeForStudent(formatter.format(ccPaymentsProxy.getOutMrDueIefSuppliedAverageCurrency()));
			// more TP fees
			response.setFullAccount(new BigDecimal(ccPaymentsProxy.getOutDueImmediatelyIefSuppliedAverageCurrency()));
			response.setDueImmediately(new BigDecimal(ccPaymentsProxy.getOutDueStudentAccountDueImmediately()));
			response.setMinimumStudyFee(new BigDecimal(ccPaymentsProxy.getOutStudyDueIefSuppliedAverageCurrency()));
			response.setMinimumForReg(new BigDecimal(ccPaymentsProxy.getOutTempSfCalculatedStudyFeesMinimum()));
			// non-tp: if smartcard balance > 0, dont show
			if (ccPaymentsProxy.getOutWsSmartCardDataBalance() > 0) {
				response.setCanChooseLibraryCard(false);
			}
			// non-tp: if mr balance > min mr fee, dont show
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
			//log.debug("CreditCardPayment: isStudentValid(); "+ response.toStringStudent());
			//------------------------------------------------------------------------------

		}

		return response;
	}
}
