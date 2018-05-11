import {StudyUnitInfo} from './';

export class StudyQuotationRequest {
  academicYear:number;
  countryCode:string;
  qualificationType:string;
  qualificationCode:string;
  libraryCard:boolean;
  matricExemption:boolean;
  courseCodes:string[];

  constructor(academicYear?:number,
              countryCode?:string,
              qualificationType?:string,
              qualificationCode?:string,
              libraryCard?:boolean,
              matricExemption?:boolean,
              ...courseCodes:string[]) {
    this.academicYear = academicYear;
    this.countryCode = countryCode;
    this.qualificationType = qualificationType;
    this.qualificationCode = qualificationCode;
    this.libraryCard = libraryCard;
    this.matricExemption = matricExemption;
    this.courseCodes = courseCodes;
  }
}
