import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/mergeMap';
import { environment } from '../../environments/environment';

import { ExamAdmissionInfo } from './../info-objects';

@Injectable()
export class ExamAdmissionService {

  private _endPoint: string;

  constructor(private http: HttpClient) {
    this._endPoint = environment.exam_admission_service_host_url;
  }

  getExamAdmissions(): Observable<ExamAdmissionInfo[]> {
    return this.http.get<ExamAdmissionInfo[]>(`${this._endPoint}/rest/examadmissions`);
  }

  getExamAdmissionsByYear(year: number): Observable<ExamAdmissionInfo[]> {
    const params = new HttpParams().set('year', <string><any>year);

    return this.http.get<ExamAdmissionInfo[]>(`${this._endPoint}/rest/examadmissions`, { params });
  }

  getExamAdmissionsByExamPeriodCode(examPeriodCode: number): Observable<ExamAdmissionInfo[]> {
    const params = new HttpParams().set('examPeriodCode', <string><any>examPeriodCode);

    return this.http.get<ExamAdmissionInfo[]>(`${this._endPoint}/rest/examadmissions`, { params });
  }

  getExamAdmissionsByExamType(examType: number): Observable<ExamAdmissionInfo[]> {
    const params = new HttpParams().set('examType', <string><any>examType);

    return this.http.get<ExamAdmissionInfo[]>(`${this._endPoint}/rest/examadmissions`, { params });
  }

  getExamAdmissionsByYearAndExamPeriodCode(year: number, examPeriodCode: number): Observable<ExamAdmissionInfo[]> {
    const params = new HttpParams()
      .set('year', <string><any>year)
      .set('examPeriodCode', <string><any>examPeriodCode);

    return this.http.get<ExamAdmissionInfo[]>(`${this._endPoint}/rest/examadmissions`, { params });
  }

  getExamAdmissionsByYearAndExamType(year: number, examType: number): Observable<ExamAdmissionInfo[]> {
    const params = new HttpParams()
      .set('year', <string><any>year)
      .set('examType', <string><any>examType);

    return this.http.get<ExamAdmissionInfo[]>(`${this._endPoint}/rest/examadmissions`, { params });
  }

  getExamAdmissionsByExamPeriodCodeAndExamType(examPeriodCode: number, examType: number): Observable<ExamAdmissionInfo[]> {
    const params = new HttpParams()
      .set('examPeriodCode', <string><any>examPeriodCode)
      .set('examType', <string><any>examType);

    return this.http.get<ExamAdmissionInfo[]>(`${this._endPoint}/rest/examadmissions`, { params });
  }

  getExamAdmissionsByYearAndExamPeriodCodeAndExamType(year: number, examPeriodCode: number, examType: number): Observable<ExamAdmissionInfo[]> {
    const params = new HttpParams()
      .set('year', <string><any>year)
      .set('examPeriodCode', <string><any>examPeriodCode)
      .set('examType', <string><any>examType);

    return this.http.get<ExamAdmissionInfo[]>(`${this._endPoint}/rest/examadmissions`, { params });
  }
}
