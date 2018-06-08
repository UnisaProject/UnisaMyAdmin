import {CreditCardStudentInfo} from "./credit-card-student-info";
import {CreditCardInfo} from "./credit-card-info";

export interface ApplicationPaymentInfo {

  studentInfo: CreditCardStudentInfo;

  applyAmountInput: number;

  creditCardTotalAmountInput: number;

  cardInfo: CreditCardInfo;

}
