import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import { RegistrationPeriodInfo, StudyFeeQuotationRequestInfo } from '../info-objects';

@Injectable()
export class RegistrationPeriodService {
  searchCriteria: StudyFeeQuotationRequestInfo = null;

  constructor(private http:HttpClient) {
  }

  getQuotationYear():Observable<number> {
       return this.http.get<number>('/myadmin-student-services/studentservices/studyfeequotation/quotationYear');
  }

  getRegistrationPeriodsByYear(year: number): Observable<RegistrationPeriodInfo[]> {
    const params = new HttpParams().set('year', <string><any>year);

    return this.http.get<RegistrationPeriodInfo[]>('/myadmin-student-services/studentservices/registrationperiods', { params });
  }

  getRegistrationPeriodsBySemester(semester: number): Observable<RegistrationPeriodInfo[]> {
    const params = new HttpParams().set('semester', <string><any>semester);

    return this.http.get<RegistrationPeriodInfo[]>('/myadmin-student-services/studentservices/registrationperiods', { params });
  }

  getRegistrationPeriodsByType(type: string): Observable<RegistrationPeriodInfo[]> {
    const params = new HttpParams().set('type', <string><any>type);

    return this.http.get<RegistrationPeriodInfo[]>('/myadmin-student-services/studentservices/registrationperiods', { params });
  }

  getRegistrationPeriodsByYearAndSemester(year: number, semester: number): Observable<RegistrationPeriodInfo[]> {
    const params = new HttpParams()
      .set('year', <string><any>year)
      .set('semester', <string><any>semester);

    return this.http.get<RegistrationPeriodInfo[]>('/myadmin-student-services/studentservices/registrationperiods', { params });
  }

  getRegistrationPeriodsByYearAndType(year: number, type: string): Observable<RegistrationPeriodInfo[]> {
    const params = new HttpParams()
      .set('year', <string><any>year)
      .set('type', <string><any>type);

    return this.http.get<RegistrationPeriodInfo[]>('/myadmin-student-services/studentservices/registrationperiods', { params });
  }

  getRegistrationPeriodsBySemesterAndType(semester: number, type: string): Observable<RegistrationPeriodInfo[]> {
    const params = new HttpParams()
      .set('semester', <string><any>semester)
      .set('type', <string><any>type);

    return this.http.get<RegistrationPeriodInfo[]>('/myadmin-student-services/studentservices/registrationperiods', { params });
  }

  getRegistrationPeriodsByYearAndSemesterAndType(year: number, semester: number, type: string): Observable<RegistrationPeriodInfo[]> {
    const params = new HttpParams()
      .set('year', <string><any>year)
      .set('semester', <string><any>semester)
      .set('type', <string><any>type);

    return this.http.get<RegistrationPeriodInfo[]>('/myadmin-student-services/studentservices/registrationperiods', { params });
  }

  getEffectiveRegistrationPeriodsByYearOnDate(year: number, date: Date): Observable<RegistrationPeriodInfo[]> {
    const params = new HttpParams()
      .set('year', <string><any>year)
      .set('date', <string><any>date);

    return this.http.get<RegistrationPeriodInfo[]>('/myadmin-student-services/studentservices/registrationperiods', { params });
  }

  getEffectiveRegistrationPeriodsBySemesterOnDate(semester: number, date: Date): Observable<RegistrationPeriodInfo[]> {
    const params = new HttpParams()
      .set('date', <string><any>date)
      .set('semester', <string><any>semester);

    return this.http.get<RegistrationPeriodInfo[]>('/myadmin-student-services/studentservices/registrationperiods', { params });
  }

  getEffectiveRegistrationPeriodsByTypeOnDate(type: string, date: Date): Observable<RegistrationPeriodInfo[]> {
    const params = new HttpParams()
      .set('type', <string><any>type)
      .set('date', <string><any>date);

    return this.http.get<RegistrationPeriodInfo[]>('/myadmin-student-services/studentservices/registrationperiods', { params });
  }

  getEffectiveRegistrationPeriodsByYearAndSemesterOnDate(year: number, semester: number, date: Date): Observable<RegistrationPeriodInfo[]> {
    const params = new HttpParams()
      .set('year', <string><any>year)
      .set('semester', <string><any>semester)
      .set('date', <string><any>date);

    return this.http.get<RegistrationPeriodInfo[]>('/myadmin-student-services/studentservices/registrationperiods', { params });
  }

  getEffectiveRegistrationPeriodsByYearAndTypeOnDate(year: number, type: string, date: Date): Observable<RegistrationPeriodInfo[]> {
    const params = new HttpParams()
      .set('year', <string><any>year)
      .set('type', <string><any>type)
      .set('date', <string><any>date);

    return this.http.get<RegistrationPeriodInfo[]>('/myadmin-student-services/studentservices/registrationperiods', { params });
  }

  getEffectiveRegistrationPeriodsBySemesterAndTypeOnDate(semester: number, type: string, date: Date): Observable<RegistrationPeriodInfo[]> {
    const params = new HttpParams()
      .set('semester', <string><any>semester)
      .set('type', <string><any>type)
      .set('date', <string><any>date);

    return this.http.get<RegistrationPeriodInfo[]>('/myadmin-student-services/studentservices/registrationperiods', { params });
  }

  getEffectiveRegistrationPeriodsByYearAndSemesterAndTypeOnDate(year: number, semester: number, type: string, date: Date): Observable<RegistrationPeriodInfo[]> {
    const params = new HttpParams()
      .set('year', <string><any>year)
      .set('semester', <string><any>semester)
      .set('type', <string><any>type)
      .set('date', <string><any>date);

    return this.http.get<RegistrationPeriodInfo[]>('/myadmin-student-services/studentservices/registrationperiods', { params });
  }

}


