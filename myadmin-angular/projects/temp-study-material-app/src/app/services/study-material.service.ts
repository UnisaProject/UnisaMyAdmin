import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {ResponseContentType} from "@angular/http";
import {Observable} from "rxjs";
import {StudentModuleEnrolmentInfo, StudyMaterialDetailInfo} from "./../info-objects/";
import {StudentInfo} from "myadmin-lib";

@Injectable()
export class StudyMaterialService {

  constructor(private http:HttpClient) {
  }

  requestStudentModuleEnrolments(studentInfo:StudentInfo):Observable<StudentModuleEnrolmentInfo[]> {
    return this.http.post<StudentModuleEnrolmentInfo[]>('/myadmin-student-services/studentservices/studymaterial/courselist', studentInfo);
  }

  requestModuleStudyMaterials(moduleCode:string, academicYear:number, semesterCode:string):Observable<StudyMaterialDetailInfo[]> {
    return this.http.get<StudyMaterialDetailInfo[]>('/myadmin-student-services/studentservices/studymaterial/viewmaterial', {
      params: {
        moduleCode: moduleCode,
        academicYear: <string><any>academicYear,
        semesterCode: semesterCode
      }
    });
  }

  downloadMaterial(moduleMaterial:StudyMaterialDetailInfo):Observable<any> {
    const options:{
      headers?:HttpHeaders,
      observe?:'body',
      params?:HttpParams,
      reportProgress?:boolean,
      responseType:'arraybuffer',
      withCredentials?:boolean
    } = {
      responseType: 'arraybuffer'
    };
    return this.http.post<any>('/myadmin-student-services/studentservices/studymaterial/download', moduleMaterial, options);
  }
}
