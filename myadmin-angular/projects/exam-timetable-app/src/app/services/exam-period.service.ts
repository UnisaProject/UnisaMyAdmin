import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ExamPeriodInfo } from '../info-objects';

@Injectable()
export class ExamPeriodService {

  constructor(private http: HttpClient) {
  }

  getExamPeriods(): Observable<ExamPeriodInfo[]> {
    return this.http.get<ExamPeriodInfo[]>('/myadmin-exam-services/services/rest/examperiodservice/examperiods' );
  }

  getExamPeriodByCodes(codes: number[]): Observable<ExamPeriodInfo[]> {
    let params = new HttpParams();
    codes.forEach(function (value) {
      params = params.append('code', <string><any>value);
    });

    return this.http.get<ExamPeriodInfo[]>('/myadmin-exam-services/services/rest/examperiodservice/examperiods', { params });
  }

}
