import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {StudentInfo} from "../info-objects/student-info";
import {StudentModuleEnrolmentInfo} from "../info-objects/student-module-enrolment-info";
import {StudyMaterialDetailInfo} from "../info-objects/study-material-detail-info";

@Injectable()
export class StudyMaterialService {

  constructor(private http:HttpClient) {
  }

  requestStudentModuleEnrolments(studentInfo:StudentInfo):Observable<StudentModuleEnrolmentInfo[]> {
    return this.http.post<StudentModuleEnrolmentInfo[]>('/myadmin-student-services/services/rest/moduleenrolmentservice/moduleEnrolment/courselist', studentInfo);
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
