import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ModuleEnrolmentInfo, StudyMaterialDetailInfo} from "../info-objects";
import {StudentInfo} from "myadmin-lib";

@Injectable()
export class StudyMaterialService {

  constructor(private http:HttpClient) {
  }

  requestStudentModuleEnrolments(studentInfo:StudentInfo):Observable<ModuleEnrolmentInfo[]> {
    return this.http.post<ModuleEnrolmentInfo[]>('/myadmin-student-services/services/rest/moduleenrolmentservice/moduleEnrolment/courselist', studentInfo);
  }

  requestModuleStudyMaterials(moduleCode:string, academicYear:number, semesterCode:string):Observable<StudyMaterialDetailInfo[]> {
    return this.http.get<StudyMaterialDetailInfo[]>('/myadmin-student-services/services/rest/studymaterialservice/studymaterial/viewmaterial', {
      params: {
        moduleCode: moduleCode,
        academicYear: <string><any>academicYear,
        semesterCode: semesterCode
      }
    });
  }
}
