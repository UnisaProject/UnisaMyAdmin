import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ExaminationInfo } from '../info-objects';

@Injectable()
export class ExaminationService {

  constructor(private http: HttpClient) {
  }

  getExaminations(): Observable<ExaminationInfo[]> {
    return this.http.get<ExaminationInfo[]>('/myadmin-student-services/examservice/examadmissions');
  }

  getExaminationsByYear(year: number): Observable<ExaminationInfo[]> {
    const params = new HttpParams().set('year', <string><any>year);

    return this.http.get<ExaminationInfo[]>('/myadmin-student-services/examservice/examadmissions', { params });
  }

  getExaminationsByExamPeriodCode(examPeriodCode: number): Observable<ExaminationInfo[]> {
    const params = new HttpParams().set('examPeriodCode', <string><any>examPeriodCode);

    return this.http.get<ExaminationInfo[]>('/myadmin-student-services/examservice/examadmissions', { params });
  }

  getExaminationsByCourseCodes(courseCodes: string[]): Observable<ExaminationInfo[]> {
    const params = new HttpParams().set('courseCodes', courseCodes.join(','));

    return this.http.get<ExaminationInfo[]>('/myadmin-student-services/examservice/examadmissions', { params });
  }

  getExaminationsByYearAndExamPeriodCode(year: number, examPeriodCode: number): Observable<ExaminationInfo[]> {
    const params = new HttpParams()
      .set('year', <string><any>year)
      .set('examPeriodCode', <string><any>examPeriodCode);

    return this.http.get<ExaminationInfo[]>('/myadmin-student-services/examservice/examadmissions', { params });
  }

  getExaminationsByYearAndCourseCodes(year: number, courseCodes: string[]): Observable<ExaminationInfo[]> {
    const params = new HttpParams()
      .set('year', <string><any>year)
      .set('courseCodes', courseCodes.join(','));

    return this.http.get<ExaminationInfo[]>('/myadmin-student-services/examservice/examadmissions', { params });
  }

  getExaminationsByExamPeriodCodeAndCourseCodes(examPeriodCode: number, courseCodes: string[]): Observable<ExaminationInfo[]> {
    const params = new HttpParams()
      .set('examPeriodCode', <string><any>examPeriodCode)
      .set('courseCodes', courseCodes.join(','));

    return this.http.get<ExaminationInfo[]>('/myadmin-student-services/examservice/examadmissions', { params });
  }

  getExaminationsByYearAndExamPeriodCodeAndCourseCodes(year: number, examPeriodCode: number, courseCodes: string[]): Observable<ExaminationInfo[]> {
    const params = new HttpParams()
      .set('year', <string><any>year)
      .set('examPeriodCode', <string><any>examPeriodCode)
      .set('courseCodes', courseCodes.join(','));

    return this.http.get<ExaminationInfo[]>('/myadmin-student-services/examservice/examadmissions', { params });
  }

}
