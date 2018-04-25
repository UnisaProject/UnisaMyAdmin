import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { environment } from '../../environments/environment';

import { ExamPeriodInfo } from './../info-objects';

@Injectable()
export class ExamPeriodService {

  private _endPoint: string;

  constructor(private http: HttpClient) {
    this._endPoint = environment.exam_period_service_host_url;
  }

  getExamPeriod(code: number): Observable<ExamPeriodInfo> {
    return this.http.get<ExamPeriodInfo>(`${this._endPoint}/rest/examperiods/${code}`);
  }

  getExamPeriods(): Observable<ExamPeriodInfo[]> {
    return this.http.get<ExamPeriodInfo[]>(`${this._endPoint}/rest/examperiods` );
  }

  getExamPeriodByCodes(codes: number[]): Observable<ExamPeriodInfo[]> {
    const params = new HttpParams().set('codes', codes.join(','));

    return this.http.get<ExamPeriodInfo[]>(`${this._endPoint}/rest/examperiods`, { params });
  }

}
