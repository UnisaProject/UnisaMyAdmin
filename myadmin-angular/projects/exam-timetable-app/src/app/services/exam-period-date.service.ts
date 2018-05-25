import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ExamPeriodDateInfo } from '../info-objects';

@Injectable()
export class ExamPeriodDateService {

  constructor(private http: HttpClient) {
  }

  getExamPeriodDates(): Observable<ExamPeriodDateInfo[]> {
    return this.http.get<ExamPeriodDateInfo[]>('rest/examperioddates');
  }

  getExamPeriodDatesByYear(year: number): Observable<ExamPeriodDateInfo[]> {
    const params = new HttpParams().set('year', <string><any>year);

    return this.http.get<ExamPeriodDateInfo[]>('rest/examperioddates', { params });
  }

  getExamPeriodDatesByExamPeriodCode(examPeriodCode: number): Observable<ExamPeriodDateInfo[]> {
    const params = new HttpParams().set('examPeriodCode', <string><any>examPeriodCode);

    return this.http.get<ExamPeriodDateInfo[]>('rest/examperioddates', { params });
  }

  getExamPeriodDatesByCourseCodes(courseCodes: string[]): Observable<ExamPeriodDateInfo[]> {
    const params = new HttpParams().set('courseCodes', courseCodes.join(','));

    return this.http.get<ExamPeriodDateInfo[]>('rest/examperioddates', { params });
  }

  getExamPeriodDatesByYearAndExamPeriodCode(year: number, examPeriodCode: number): Observable<ExamPeriodDateInfo[]> {
    const params = new HttpParams()
      .set('year', <string><any>year)
      .set('examPeriodCode', <string><any>examPeriodCode);

    return this.http.get<ExamPeriodDateInfo[]>('rest/examperioddates', { params });
  }

  getExamPeriodDatesByYearAndCourseCodes(year: number, courseCodes: string[]): Observable<ExamPeriodDateInfo[]> {
    const params = new HttpParams()
      .set('year', <string><any>year)
      .set('courseCodes', courseCodes.join(','));

    return this.http.get<ExamPeriodDateInfo[]>('rest/examperioddates', { params });
  }

  getExamPeriodDatesByExamPeriodCodeAndCourseCodes(examPeriodCode: number, courseCodes: string[]): Observable<ExamPeriodDateInfo[]> {
    const params = new HttpParams()
      .set('examPeriodCode', <string><any>examPeriodCode)
      .set('courseCodes', courseCodes.join(','));

    return this.http.get<ExamPeriodDateInfo[]>('rest/examperioddates', { params });
  }

  getExamPeriodDatesByYearAndExamPeriodCodeAndCourseCodes(year: number, examPeriodCode: number, courseCodes: string[]): Observable<ExamPeriodDateInfo[]> {
    const params = new HttpParams()
      .set('year', <string><any>year)
      .set('examPeriodCode', <string><any>examPeriodCode)
      .set('courseCodes', courseCodes.join(','));

    return this.http.get<ExamPeriodDateInfo[]>('rest/examperioddates', { params });
  }

}
