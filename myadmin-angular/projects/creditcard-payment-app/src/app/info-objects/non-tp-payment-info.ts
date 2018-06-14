import {QualificationInfo} from "./qualification-info";
import {CreditCardInfo} from "./credit-card-info";
import {CreditCardPaymentInfo} from "./credit-card-payment-info";
import {CreditCardStudentInfo} from "./credit-card-student-info";

export class NonTpPaymentInfo {

  studentInfo:CreditCardStudentInfo;

  qualificationInfo:QualificationInfo;

  creditCardInfo:CreditCardInfo;

  regStatus:string;

  regStatusDescription:string;

  //StudyFee Balance
  balance:number;

  creditDebitIndicator:string;

  libraryFineBalance:number;

  libCreditDebitIndicator:string;

  // set NON-TP matric +lib fees
  canChooseLibraryCard:boolean;
  payLibraryFee:boolean;
  libraryFee:number;
  canChooseMatric:boolean;
  payMatricFirstAppFee:boolean;
  matricFirstAppFee:number;
  canChooseThreeGDataBundle:boolean;
  payThreeGDataBundleFee:boolean;
  threeGDataBundleFee:number;

  libraryFineFee:number;
  studyFeeAmount:number;
  //private number applyAmount; //??

  creditCardTotalAmountInput:number;

  constructor(options:CreditCardPaymentInfo) {
    this.studentInfo = options.studentInfo;
    this.qualificationInfo = options.qualificationInfo;
    this.creditCardInfo = options.creditCardInfo;
    this.regStatus = options.regStatus;
    this.regStatusDescription = options.regStatusDescription;
    this.balance = options.balance;
    this.creditDebitIndicator = options.creditDebitIndicator;
    this.libraryFineBalance = options.librayFineBalance;
    this.libCreditDebitIndicator = options.libCreditDebitIndicator;
    this.canChooseLibraryCard = options.canChooseLibraryCard;
    this.payLibraryFee = false;
    this.libraryFee = options.libraryFee;
    this.canChooseMatric = options.canChooseMatric;
    this.payMatricFirstAppFee = false;
    this.matricFirstAppFee = options.matricFirstAppFee;
    this.canChooseThreeGDataBundle = options.canChooseThreeGDataBundle;
    this.payThreeGDataBundleFee = false;
    this.threeGDataBundleFee = options.threeGDataBundleFee;
    this.libraryFineFee = options.libraryFineFee;
    this.studyFeeAmount = 0;
    this.creditCardTotalAmountInput = 0;
  }
}
