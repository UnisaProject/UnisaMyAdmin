import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ErrorInfo}from 'myadmin-lib';
import {AcademicRecordEmailRequestInfo, StudentAcademicQualificationRecordInfo} from './../info-objects';

@Injectable()
export class AcademicRecordService {

  constructor(private http: HttpClient) { }

  getStudentAcademicQualificationResults(studentNumber: number): Observable<StudentAcademicQualificationRecordInfo[]> {
    return this.http.get<StudentAcademicQualificationRecordInfo[]>('/myadmin-student-services/services/rest/studentacademicrecordservice/academicrecord/qualifications',{
      params : {
        studentNumber: <string><any>studentNumber
      }
    });
  }

  sendStudentAcademicQualificationEmail(emailRequestInfo :AcademicRecordEmailRequestInfo): Observable<ErrorInfo> {
    return this.http.post<ErrorInfo>('/myadmin-student-services/services/rest/studentacademicrecordservice/academicrecord/email',emailRequestInfo);
  }
}
