import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { ExamPeriodInfo } from './../info-objects';

@Injectable()
export class ExamPeriodService {

  constructor(private http: HttpClient) {
  }

  getExamPeriod(code: number): Observable<ExamPeriodInfo> {
    return this.http.get<ExamPeriodInfo>('/rest/examperiods/${code}');
  }

  getExamPeriods(): Observable<ExamPeriodInfo[]> {
    return this.http.get<ExamPeriodInfo[]>('/rest/examperiods' );
  }

  getExamPeriodByCodes(codes: number[]): Observable<ExamPeriodInfo[]> {
    const params = new HttpParams().set('codes', codes.join(','));

    return this.http.get<ExamPeriodInfo[]>('/rest/examperiods', { params });
  }

}
