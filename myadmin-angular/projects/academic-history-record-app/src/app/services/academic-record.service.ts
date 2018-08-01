import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable()
export class AcademicRecordService {

  constructor(private http: HttpClient) { }

  getStudentAcademicQualificationResults(studentNumber: number): Observable<any> {
    return this.http.get<any>('/myadmin-payment-services/services/rest/studentacademicrecordservice/studentacademicqualifications',{
      params : {
        studentNumber: <string><any>studentNumber
      }
    });
  }
}
