import {StudyUnitInfo} from './';
import {StudyQuotationRequest} from "./study-quotation-request";

export class StudyQuotation extends StudyQuotationRequest {

  registrationFee:string;
  libraryCardCost:number;
  matricExemptionCost:number;
  prescribedBooks:number;
  foreignLevy:number;
  totalFee:number;
  paymentDue:number;
  studyUnits:StudyUnitInfo[];

  constructor(academicYear?:number,
              countryCode?:string,
              qualificationType?:string,
              qualificationCode?:string,
              libraryCard?:boolean,
              matricExemption?:boolean,
              registrationFee?:string,
              libraryCardCost?:number,
              matricExemptionCost?:number,
              prescribedBooks?:number,
              foreignLevy?:number,
              totalFee?:number,
              studyUnits?:StudyUnitInfo[],
              paymentDue?:number,
              ...courseCodes:string[]) {
    super(academicYear,
      countryCode,
      qualificationType,
      qualificationCode,
      libraryCard,
      matricExemption,
      ...courseCodes);
    this.registrationFee = registrationFee;
    this.libraryCardCost = libraryCardCost;
    this.matricExemptionCost = matricExemptionCost;
    this.prescribedBooks = prescribedBooks;
    this.foreignLevy = foreignLevy;
    this.totalFee = totalFee;
    this.paymentDue = paymentDue;
    this.studyUnits = studyUnits;
  }

}
