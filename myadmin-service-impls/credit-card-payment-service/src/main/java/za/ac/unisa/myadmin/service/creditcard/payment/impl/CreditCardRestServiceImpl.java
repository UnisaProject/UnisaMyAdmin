package za.ac.unisa.myadmin.service.creditcard.payment.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.creditcard.payment.ApplicationPaymentInfo;
import za.ac.unisa.myadmin.creditcard.payment.CreditCardPaymentInfo;
import za.ac.unisa.myadmin.creditcard.payment.CreditCardPaymentService;
import za.ac.unisa.myadmin.creditcard.payment.NonTpPaymentInfo;
import za.ac.unisa.myadmin.creditcard.payment.SummaryInfo;
import za.ac.unisa.myadmin.creditcard.payment.TpPaymentInfo;
import za.ac.unisa.myadmin.service.creditcard.payment.validator.ApplicationPaymentInfoValidator;
import za.ac.unisa.myadmin.service.creditcard.payment.validator.CardStudentInfoValidator;
import za.ac.unisa.myadmin.service.creditcard.payment.validator.CreditCardInfoValidator;
import za.ac.unisa.myadmin.service.creditcard.payment.validator.NonTpPaymentInfoValidator;
import za.ac.unisa.myadmin.service.creditcard.payment.validator.TpPaymentInfoValidator;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Created by Adrian on 2018-06-04.
 */
@RestController
@RequestMapping({"/rest"})
public class CreditCardRestServiceImpl {

	@Autowired
	@Qualifier("CreditCardPaymentService")
	CreditCardPaymentService creditCardPaymentService;

	@InitBinder("applicationPaymentInfo")
	protected void initApplyPaymentBinder(WebDataBinder binder) {
		binder.setValidator(new ApplicationPaymentInfoValidator(new CreditCardInfoValidator(), new CardStudentInfoValidator()));
	}
	@InitBinder("nonTpPaymentInfo")
	protected void initNonTpBinder(WebDataBinder binder) {
		binder.setValidator(new NonTpPaymentInfoValidator(new CreditCardInfoValidator(), new CardStudentInfoValidator()));
	}
	@InitBinder("tpPaymentInfo")
	protected void initTpBinder(WebDataBinder binder) {
		binder.setValidator(new TpPaymentInfoValidator(new CreditCardInfoValidator(), new CardStudentInfoValidator()));
	}

	@GetMapping(path = {"/creditcardpayment/studentinput"}, produces = APPLICATION_JSON_VALUE)
	public CreditCardPaymentInfo processStudentInput(@RequestParam(value = "studentNumber") Integer studentNumber) throws OperationFailedException {
		return creditCardPaymentService.processStudentInput(studentNumber);
	}

	@GetMapping(path = {"/creditcardpayment/qualinput"}, produces = APPLICATION_JSON_VALUE)
	public CreditCardPaymentInfo processQualInput(@RequestParam(value = "studentNumber") Integer studentNumber, @RequestParam(value = "qualCode") String qualCode) throws OperationFailedException {
		return creditCardPaymentService.processQualInput(studentNumber, qualCode);
	}

	@GetMapping(path = {"/creditcardpayment/smartCardValue"}, produces = APPLICATION_JSON_VALUE)
	public String getSmartCardValue(@RequestParam(value = "studentNumber") Integer studentNumber) throws OperationFailedException {
		return creditCardPaymentService.getSmartCardValue(studentNumber);
	}

	@PutMapping(path = {"/creditcardpayment/smartCardValue"}, produces = APPLICATION_JSON_VALUE)
	public int updateSmartCardValue(@RequestParam(value = "smartCard") String smartCard, @RequestParam(value = "studentNumber") Integer studentNumber) throws OperationFailedException {
		return creditCardPaymentService.updateSmartCardValue(smartCard, studentNumber);
	}

	@PostMapping(path = "/creditcardpayment/applyNonTPPayment", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public SummaryInfo processNonTpPayment(@Validated @RequestBody NonTpPaymentInfo nonTpPaymentInfo) throws OperationFailedException {
		return creditCardPaymentService.processNonTpPayment(nonTpPaymentInfo);
	}

	@PostMapping(path = "/creditcardpayment/applyTPPayment", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public SummaryInfo processTpPayment(@Validated @RequestBody TpPaymentInfo tpPaymentInfo) throws OperationFailedException {
		return creditCardPaymentService.processTpPayment(tpPaymentInfo);
	}

	@PostMapping(path = "/creditcardpayment/applyPayment", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public SummaryInfo processApplyPayment(@Validated @RequestBody ApplicationPaymentInfo applicationPaymentInfo) throws OperationFailedException {
		return creditCardPaymentService.processApplicationPayment(applicationPaymentInfo);
	}

}
