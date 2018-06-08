import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {CreditCardPaymentInfo} from "../info-objects";

@Injectable()
export class CreditCardPaymentService {

  constructor(private http: HttpClient) {
  }


  studentInput(studentNumber:string): Observable<CreditCardPaymentInfo> {
    return this.http.get<CreditCardPaymentInfo>('/myadmin-student-services/rest/studentinput',{
      params : {
        studentNumber: studentNumber
      }
    });
  }

  getSmartCardValue(studentNumber: string): Observable<string>{
    return this.http.get<string>('/myadmin-student-services/rest/smartCardValue',{
      params : {
        studentNumber: studentNumber
      }
    });
  }
}
