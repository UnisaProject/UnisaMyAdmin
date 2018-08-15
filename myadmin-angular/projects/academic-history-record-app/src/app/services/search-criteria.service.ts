import {Injectable} from "@angular/core";
import {StudentInfo} from "myadmin-lib";
import {StudentAcademicQualificationRecordInfo,AcademicModuleRecordInfo} from './../info-objects';
@Injectable()
export class SearchCriteriaService {
  studentInfo: StudentInfo = null;
  selectedQualification: StudentAcademicQualificationRecordInfo = null;
  academicQualResults: StudentAcademicQualificationRecordInfo[] = [];
  qualModuleResults: AcademicModuleRecordInfo[] = [];

  constructor() {
  }
}
