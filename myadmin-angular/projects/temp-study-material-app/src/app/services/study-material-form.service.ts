import {Injectable} from "@angular/core";
import {StudentInfo} from "myadmin-lib";
import {StudentModuleEnrolmentInfo, StudyMaterialDetailInfo} from "./../info-objects/";

@Injectable()
export class StudyMaterialFormService {

  studentInfo:StudentInfo = null;

  studentModuleEnrolmentList:StudentModuleEnrolmentInfo[] = null;

  selectedModule:StudentModuleEnrolmentInfo = null;

  studentModuleMaterialList:StudyMaterialDetailInfo[] = null;

  constructor() {
  }
}
