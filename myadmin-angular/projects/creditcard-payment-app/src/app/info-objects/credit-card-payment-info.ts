import {StudentInfo} from "myadmin-lib";
import {QualificationInfo} from "./qualification-info";
import {CreditCardInfo} from "./credit-card-info";

export interface CreditCardPaymentInfo {
  regStatus?:string;
  regStatusDescription?:string;
  balance?:number;
  creditDebitIndicator?:string;
  libraryFineBalance?:number;
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

  //canCancelSmartCard:boolean;

  studentInfo?:StudentInfo;
  qualificationInfo?: QualificationInfo;
  creditCardInfo?: CreditCardInfo;
}
