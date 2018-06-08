import {CreditCardStudentInfo} from "./credit-card-student-info";
import {QualificationInfo} from "./qualification-info";
import {CreditCardInfo} from "./credit-card-info";

export interface CreditCardPaymentInfo {
  regStatus?:string;
  regStatusDescription?:string;
  balance?:number;
  librayFineBalance?:number;
  libCreditDebitIndicator?:string;

  // set NON-TP matric +lib fees
  libraryFee: number;
  libraryFineFee: number;
  threeGDataBundleFee: number;
  matricFirstAppFee: number;

  //TP Fees
  libraryFeeForStudent: number;
  libraryFineFeeForStudent: number;
  threeGDataBundleFeeForStudent: number;
  matricFeeForStudent: number;
  fullAccount: number;
  dueImmediately: number;
  minimumStudyFee: number;
  minimumForReg: number;

  canChooseLibraryCard?:boolean;
  canChooseMatric?:boolean;
  canChooseThreeGDataBundle?:boolean;
  applyAmount?:number;
  creditDebitIndicator?:string;

  canCancelSmartCard:boolean;

  studentInfo?:CreditCardStudentInfo;
  qualificationInfo?: QualificationInfo;
  creditCardInfo?: CreditCardInfo;
}
