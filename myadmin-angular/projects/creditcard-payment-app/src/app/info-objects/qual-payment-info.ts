import {QualificationInfo} from "./qualification-info";
import {CreditCardStudentInfo} from "./credit-card-student-info";

export interface QualPaymentInfo {

  balance:number;
  creditDebitIndicator:String;

  libraryFineBalance:number;
  libCreditDebitIndicator:String;

  fullAccount:number;
  dueImmediately:number;
  minimumStudyFee:number;
  minimumForReg:number;

  studentInfo:CreditCardStudentInfo;

  qualificationInfo:QualificationInfo;
  
}
