package za.ac.unisa.myadmin.payment.services.decorators;

import java.math.BigDecimal;

import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.payment.services.PaymentService;
import za.ac.unisa.myadmin.payment.services.dto.SummaryInfo;
import za.ac.unisa.myadmin.payment.services.dto.TpPaymentInfo;
import za.ac.unisa.myadmin.services.base.decorators.PaymentServiceDecorator;
import za.ac.unisa.myadmin.student.services.StudentService;

public class PaymentServiceStudentDecorator extends PaymentServiceDecorator implements PaymentService {

	private StudentService studentService;

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	@Override
	public SummaryInfo processTpPayment(TpPaymentInfo paymentInfo)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		// student indicated that he/she wants to cancel the library fee
		if (paymentInfo.getLibraryFeeForStudent().compareTo(BigDecimal.ZERO) > 0) {
			studentService.updateSmartCardValue("W", Integer.valueOf(paymentInfo.getStudentInfo().getStudentNumber()));
		}

		return getNextDecorator().processTpPayment(paymentInfo);
	}

}
