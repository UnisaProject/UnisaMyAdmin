import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ExaminationInfo } from '../info-objects';

@Injectable()
export class ExaminationService {

  constructor(private http: HttpClient) {
  }

  getExaminationsByYearAndExamPeriodCodeAndCourseCodes(year: number, examPeriodCode: number, courseCodes: string[]): Observable<ExaminationInfo[]> {
    let params = new HttpParams()
      .set('year', <string><any>year)
      .set('examPeriodCode', <string><any>examPeriodCode);
    courseCodes.forEach(function (value) {
      params = params.append('courseCode', value);
    });

    return this.http.get<ExaminationInfo[]>('/myadmin-exam-services/services/rest/examinationservice/examinations', { params });
  }

}
