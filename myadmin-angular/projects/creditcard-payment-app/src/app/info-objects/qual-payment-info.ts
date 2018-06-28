import {QualificationInfo} from "./qualification-info";
import {StudentInfo} from "myadmin-lib";

export interface QualPaymentInfo {

  balance:number;
  creditDebitIndicator:String;

  libraryFineBalance:number;
  libCreditDebitIndicator:String;

  fullAccount:number;
  dueImmediately:number;
  minimumStudyFee:number;
  minimumForReg:number;

  studentInfo:StudentInfo;

  qualificationInfo:QualificationInfo;

}
