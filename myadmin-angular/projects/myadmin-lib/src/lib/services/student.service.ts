import {Injectable} from "@angular/core";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {StudentInfo} from "../info-objects";

@Injectable()
export class StudentService {

  constructor(private http:HttpClient) {
  }

  getStudentsBySurname(surname:string):Observable<StudentInfo[]> {
    const params = new HttpParams().set('surname', <string><any>surname);

    return this.http.get<StudentInfo[]>('/myadmin-student-services/services/rest/studentservice/students', {params});
  }

  getStudentsByFirstname(firstName:string):Observable<StudentInfo[]> {
    const params = new HttpParams().set('firstName', <string><any>firstName);

    return this.http.get<StudentInfo[]>('/myadmin-student-services/services/rest/studentservice/students', {params});
  }

  getStudentsBySurnameAndFirstname(surname:string, firstName:string):Observable<StudentInfo[]> {
    const params = new HttpParams()
      .set('surname', surname)
      .set('firstName', firstName);

    return this.http.get<StudentInfo[]>('/myadmin-student-services/services/rest/studentservice/students', {params});
  }

  getStudentsBySurnameAndFirstnameAndBirthDate(surname:string, firstName:string, dateOfBirth:string):Observable<StudentInfo[]> {
    const params = new HttpParams()
      .set('surname', <string><any>surname)
      .set('firstName', <string><any>firstName)
      .set('dateOfBirth', <string><any>dateOfBirth);

    return this.http.get<StudentInfo[]>('/myadmin-student-services/services/rest/studentservice/students', {params});
  }

  getStudentsBySurnameAndFirstnameAndBirthDateAndIdNumber(surname:string, firstName:string, dateOfBirth:string, identityNumber:string):Observable<StudentInfo[]> {
    const params = new HttpParams()
      .set('surname', <string><any>surname)
      .set('firstName', <string><any>firstName)
      .set('dateOfBirth', <string><any>dateOfBirth)
      .set('identityNumber', <string><any>identityNumber);

    return this.http.get<StudentInfo[]>('/myadmin-student-services/services/rest/studentservice/students', {params});
  }

  getStudentsBySurnameAndFirstnameAndBirthDateAndPassportNumber(surname:string, firstName:string, dateOfBirth:string, passport:string):Observable<StudentInfo[]> {
    const params = new HttpParams()
      .set('surname', <string><any>surname)
      .set('firstName', <string><any>firstName)
      .set('dateOfBirth', <string><any>dateOfBirth)
      .set('passportNumber', <string><any>passport);

    return this.http.get<StudentInfo[]>('/myadmin-student-services/services/rest/studentservice/students', {params});
  }

}
