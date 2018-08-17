import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {AcademicModuleRecordInfo} from './../info-objects';

@Injectable()
export class AcademicRecordModuleService {

  constructor(private http:HttpClient) {
  }

  getStudentAcademicModuleResults(studentNumber: number, selectedQualificationCode: string, isCreditsOnly: boolean): Observable<AcademicModuleRecordInfo[]> {
    return this.http.get<AcademicModuleRecordInfo[]>('/myadmin-student-services/services/rest/academicmodulerecordservice/academicmodules',{
      params : {
        studentNumber: <string><any>studentNumber,
        isCreditsOnly: <string><any>isCreditsOnly,
        selectedQualificationCode: <string><any>selectedQualificationCode
      }
    });
  }
}
