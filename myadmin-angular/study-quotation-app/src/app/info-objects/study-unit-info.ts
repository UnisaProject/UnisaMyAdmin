export class StudyUnitInfo {
  studyUnitcode:string;
  description:string;
  fee:number;

  constructor(studyUnitcode?:string,
              description?:string,
              fee?:number) {
    this.studyUnitcode = studyUnitcode;
    this.description = description;
    this.fee = fee;
  }

}
