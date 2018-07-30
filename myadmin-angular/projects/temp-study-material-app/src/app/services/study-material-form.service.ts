import {Injectable} from "@angular/core";
import {StudentInfo, StudyMaterialDetailInfo, ModuleEnrolmentInfo} from "myadmin-lib";
@Injectable()
export class StudyMaterialFormService {

  studentInfo:StudentInfo = null;

  studentModuleEnrolmentList:ModuleEnrolmentInfo[] = null;

  selectedModule:ModuleEnrolmentInfo = null;

  studentModuleMaterialList:StudyMaterialDetailInfo[] = null;

  constructor() {
  }
}
