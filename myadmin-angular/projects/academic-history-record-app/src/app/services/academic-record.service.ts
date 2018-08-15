import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ErrorInfo}from 'myadmin-lib';
import {StudentAcademicQualificationRecordInfo} from './../info-objects';

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

  sendStudentAcademicQualificationEmail(studentNumber: number, academicQualCode: string, isAttachMarks: boolean): Observable<ErrorInfo> {
    return this.http.get<ErrorInfo>('/myadmin-student-services/services/rest/studentacademicrecordservice/academicrecord/email',{
      params : {
        studentNumber: <string><any>studentNumber,
        academicQualCode: <string><any>academicQualCode,
        isAttachMarks: <string><any>isAttachMarks
      }
    });
  }
}
