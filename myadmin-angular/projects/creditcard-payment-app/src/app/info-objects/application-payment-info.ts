import {CreditCardStudentInfo} from "./credit-card-student-info";
import {CreditCardInfo} from "./credit-card-info";
import {CreditCardPaymentInfo} from "./credit-card-payment-info";

export class ApplicationPaymentInfo {

  studentInfo:CreditCardStudentInfo;

  applyAmountInput:number;

  creditCardTotalAmountInput:number;

  cardInfo:CreditCardInfo;

  constructor(options:CreditCardPaymentInfo) {
    this.studentInfo = options.studentInfo;
    this.cardInfo = options.creditCardInfo;
    this.applyAmountInput = options.applyAmount;
    this.creditCardTotalAmountInput = options.applyAmount;
  }
}
