package za.ac.unisa.myadmin.service.creditcard.payment.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.creditcard.payment.CreditCardPaymentInfo;
import za.ac.unisa.myadmin.creditcard.payment.CreditCardPaymentService;

/**
 * Created by Adrian on 2018-06-04.
 */
@RestController
@RequestMapping({"/rest"})
public class CreditCardRestServiceImpl {

	@Autowired
	@Qualifier("CreditCardPaymentService")
	CreditCardPaymentService creditCardPaymentService;

	@GetMapping(path = {"/studentinput"})
	public CreditCardPaymentInfo processStudentInput(@RequestParam(value = "studentNumber", required = true) Integer studentNumber) throws OperationFailedException {
		return creditCardPaymentService.processStudentInput(studentNumber);
	}
}
