import {Injectable} from "@angular/core";
import {StudentInfo} from "myadmin-lib";
import {ModuleEnrolmentInfo, StudyMaterialDetailInfo} from "myadmin-lib";

@Injectable()
export class StudyMaterialFormService {

  studentInfo:StudentInfo = null;

  studentModuleEnrolmentList:ModuleEnrolmentInfo[] = null;

  selectedModule:ModuleEnrolmentInfo = null;

  studentModuleMaterialList:StudyMaterialDetailInfo[] = null;

  constructor() {
  }
}
