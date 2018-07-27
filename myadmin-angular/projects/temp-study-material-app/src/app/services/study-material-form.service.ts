import {Injectable} from "@angular/core";
import {StudentInfo, StudyMaterialDetailInfo, StudentModuleEnrolmentInfo} from "myadmin-lib";
@Injectable()
export class StudyMaterialFormService {

  studentInfo:StudentInfo = null;

  studentModuleEnrolmentList:StudentModuleEnrolmentInfo[] = null;

  selectedModule:StudentModuleEnrolmentInfo = null;

  studentModuleMaterialList:StudyMaterialDetailInfo[] = null;

  constructor() {
  }
}
