import {StudyUnitInfo} from './';

export class StudyQuotationRequest {
  academicYear:number;
  countryCode:string;
  qualificationType:string;
  qualificationCode:string;
  libraryCard:boolean;
  matricExemption:boolean;
  courseCodes:string[];
  studyUnits:StudyUnitInfo[];

  constructor(academicYear?:number,
              countryCode?:string,
              qualificationType?:string,
              qualificationCode?:string,
              libraryCard?:boolean,
              matricExemption?:boolean,
              studyUnits?:StudyUnitInfo[],
              ...courseCodes:string[]) {
    this.academicYear = academicYear;
    this.countryCode = countryCode;
    this.qualificationType = qualificationType;
    this.qualificationCode = qualificationCode;
    this.libraryCard = libraryCard;
    this.matricExemption = matricExemption;
    this.courseCodes = courseCodes;
    this.studyUnits = studyUnits;
  }
}
