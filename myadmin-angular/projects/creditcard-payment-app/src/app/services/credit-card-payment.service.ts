import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {CreditCardPaymentInfo} from "../info-objects";
import {ApplicationPaymentInfo} from "../info-objects/application-payment-info";
import {SummaryInfo} from "../info-objects/summary-info";

@Injectable()
export class CreditCardPaymentService {

  constructor(private http: HttpClient) {
  }


  processStudentInput(studentNumber:string): Observable<CreditCardPaymentInfo> {
    return this.http.get<CreditCardPaymentInfo>('/myadmin-student-services/rest/creditcardpayment/studentinput',{
      params : {
        studentNumber: studentNumber
      }
    });
  }

  processQualInput(studentNumber:string, qualCode:string): Observable<CreditCardPaymentInfo> {
    return this.http.get<CreditCardPaymentInfo>('/myadmin-student-services/rest/creditcardpayment/qualinput',{
      params : {
        studentNumber: studentNumber,
        qualCode : qualCode
      }
    });
  }

  updateSmartCardValue(smartCard: string, studentNumber: string): Observable<string>{
    return this.http.put<string>('/myadmin-student-services/rest/creditcardpayment/smartCardValue',null,{
      params : {
        studentNumber: studentNumber,
        smartCard: smartCard,
      }
    });
  }


  getSmartCardValue(studentNumber: string): Observable<string>{
    return this.http.get<string>('/myadmin-student-services/rest/creditcardpayment/smartCardValue',{
      params : {
        studentNumber: studentNumber
      }
    });
  }




  processApplicationPayment(paymentInfo: ApplicationPaymentInfo): Observable<SummaryInfo>{
    return this.http.post<SummaryInfo>('/myadmin-student-services/rest/creditcardpayment/smartCardValue', paymentInfo);
  }


}
