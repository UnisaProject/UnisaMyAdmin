import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ExamAdmissionInfo } from '../info-objects';

@Injectable()
export class ExamAdmissionService {

  constructor(private http: HttpClient) {
  }

  getExamAdmissions(): Observable<ExamAdmissionInfo[]> {
    return this.http.get<ExamAdmissionInfo[]>('/myadmin-exam-services/examservices/examadmissions');
  }

  getExamAdmissionsByYear(year: number): Observable<ExamAdmissionInfo[]> {
    const params = new HttpParams().set('year', <string><any>year);

    return this.http.get<ExamAdmissionInfo[]>('/myadmin-exam-services/examservices/examadmissions', { params });
  }

  getExamAdmissionsByExamPeriodCode(examPeriodCode: number): Observable<ExamAdmissionInfo[]> {
    const params = new HttpParams().set('examPeriodCode', <string><any>examPeriodCode);

    return this.http.get<ExamAdmissionInfo[]>('/myadmin-exam-services/examservices/examadmissions', { params });
  }

  getExamAdmissionsByExamType(examType: number): Observable<ExamAdmissionInfo[]> {
    const params = new HttpParams().set('examType', <string><any>examType);

    return this.http.get<ExamAdmissionInfo[]>('/myadmin-exam-services/examservices/examadmissions', { params });
  }

  getExamAdmissionsByYearAndExamPeriodCode(year: number, examPeriodCode: number): Observable<ExamAdmissionInfo[]> {
    const params = new HttpParams()
      .set('year', <string><any>year)
      .set('examPeriodCode', <string><any>examPeriodCode);

    return this.http.get<ExamAdmissionInfo[]>('/myadmin-exam-services/examservices/examadmissions', { params });
  }

  getExamAdmissionsByYearAndExamType(year: number, examType: number): Observable<ExamAdmissionInfo[]> {
    const params = new HttpParams()
      .set('year', <string><any>year)
      .set('examType', <string><any>examType);

    return this.http.get<ExamAdmissionInfo[]>('/myadmin-exam-services/examservices/examadmissions', { params });
  }

  getExamAdmissionsByExamPeriodCodeAndExamType(examPeriodCode: number, examType: number): Observable<ExamAdmissionInfo[]> {
    const params = new HttpParams()
      .set('examPeriodCode', <string><any>examPeriodCode)
      .set('examType', <string><any>examType);

    return this.http.get<ExamAdmissionInfo[]>('/myadmin-exam-services/examservices/examadmissions', { params });
  }

  getExamAdmissionsByYearAndExamPeriodCodeAndExamType(year: number, examPeriodCode: number, examType: number): Observable<ExamAdmissionInfo[]> {
    const params = new HttpParams()
      .set('year', <string><any>year)
      .set('examPeriodCode', <string><any>examPeriodCode)
      .set('examType', <string><any>examType);

    return this.http.get<ExamAdmissionInfo[]>('/myadmin-exam-services/examservices/examadmissions', { params });
  }
}
