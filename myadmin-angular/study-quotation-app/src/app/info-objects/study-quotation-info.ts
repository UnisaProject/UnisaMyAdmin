import {StudyUnitInfo} from './';

export class StudyQuotationInfo {
  academicYear:number;
  countryCode:string;
  qualificationType:string;
  qualificationCode:string;
  libraryCard:string;
  matricExemption:string;
  courseCodes:string[];
  studyUnits:StudyUnitInfo[];

  constructor(academicYear?:number,
              countryCode?:string,
              qualificationType?:string,
              qualificationCode?:string,
              libraryCard?:string,
              matricExemption?:string,
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
