import {QualificationInfo} from "./qualification-info";
import {CreditCardInfo} from "./credit-card-info";
import {CreditCardPaymentInfo} from "./credit-card-payment-info";
import {CreditCardStudentInfo} from "./credit-card-student-info";

export class TpPaymentInfo {

  studentInfo: CreditCardStudentInfo;

  qualificationInfo: QualificationInfo;

  creditCardInfo: CreditCardInfo;

  regStatus: string;

  regStatusDescription: string;

  balance: number;

  creditDebitIndicator: string;

  libraryFineBalance: number;

  libCreditDebitIndicator: string;

  dueImmediately: number;
  minimumForReg: number;
  fullAccount: number;

  //TP Fees
  libraryFineFeeForStudent: number;

  minimumStudyFee: number;
  studyFeeAmount: number;

  libraryFeeForStudent: number;
  // indicate:to whether a can:student cancel his smartcard request
  //canSmartCardCancel: boolean;
  //cancelSmartCard: boolean;

  matricFeeForStudent: number;

  creditCardTotalAmountInput: number;

  constructor(options:CreditCardPaymentInfo) {
    this.studentInfo = options.studentInfo;
    this.qualificationInfo = options.qualificationInfo;
    this.creditCardInfo = options.creditCardInfo;
    this.regStatus = options.regStatus;
    this.regStatusDescription = options.regStatusDescription;
    this.balance = options.balance || 0;
    this.creditDebitIndicator = options.creditDebitIndicator || '';
    this.libraryFineBalance = options.libraryFineBalance || 0;
    this.libCreditDebitIndicator = options.libCreditDebitIndicator || '';
    this.dueImmediately = options.dueImmediately;
    this.minimumForReg = options.minimumForReg;
    this.fullAccount = options.fullAccount;
    this.libraryFineFeeForStudent = options.libraryFineFeeForStudent;
    this.minimumStudyFee = options.minimumStudyFee;
    this.studyFeeAmount = 0;
    this.libraryFeeForStudent = options.libraryFeeForStudent;
    //this.canSmartCardCancel = options.canCancelSmartCard;
    //this.cancelSmartCard = false;
    this.matricFeeForStudent = options.matricFeeForStudent;
    this.creditCardTotalAmountInput = 0;
  }
}
