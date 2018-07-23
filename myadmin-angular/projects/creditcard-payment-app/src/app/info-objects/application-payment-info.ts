import {CreditCardInfo} from "./credit-card-info";
import {CreditCardPaymentInfo} from "./credit-card-payment-info";
import {StudentInfo} from "myadmin-lib";
export class ApplicationPaymentInfo {

  studentInfo:StudentInfo;

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
