import {Injectable} from "@angular/core";
import {StudentInfo} from "myadmin-lib";
import {AcademicModuleResultInfo,AcademicQualResultInfo} from './../info-objects';
@Injectable()
export class SearchCriteriaService {
  studentInfo: StudentInfo = null;
  selectedQualification: AcademicQualResultInfo = null;
  academicQualResults: AcademicQualResultInfo[] = [];
  qualModuleResults: AcademicModuleResultInfo[] = [];

  constructor() {
  }
}
