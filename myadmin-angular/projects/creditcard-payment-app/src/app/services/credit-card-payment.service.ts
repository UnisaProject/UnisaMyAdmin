import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {CreditCardPaymentInfo} from "../info-objects";
import {ApplicationPaymentInfo} from "../info-objects/application-payment-info";
import {SummaryInfo} from "../info-objects/summary-info";
import {TpPaymentInfo} from "../info-objects/tp-payment-info";
import {NonTpPaymentInfo} from "../info-objects/non-tp-payment-info";
import {QualPaymentInfo} from "../info-objects/qual-payment-info";

@Injectable()
export class CreditCardPaymentService {

  constructor(private http: HttpClient) {
  }


  processStudentInput(studentNumber: number): Observable<CreditCardPaymentInfo> {
    return this.http.get<CreditCardPaymentInfo>('/myadmin-payment-services/services/rest/paymentservice/payment/studentinput',{
      params : {
        studentNumber: <string><any>studentNumber
      }
    });
  }

  processQualInput(studentNumber: number, qualCode:string): Observable<QualPaymentInfo> {
    return this.http.get<QualPaymentInfo>('/myadmin-payment-services/services/rest/paymentservice/payment/qualinput',{
      params : {
        studentNumber: <string><any>studentNumber,
        qualCode : qualCode
      }
    });
  }

  processApplicationPayment(paymentInfo: ApplicationPaymentInfo): Observable<SummaryInfo>{
    return this.http.post<SummaryInfo>('/myadmin-payment-services/services/rest/paymentservice/payment/applyPayment', paymentInfo);
  }

  processNonTpPayment(paymentInfo: NonTpPaymentInfo): Observable<SummaryInfo> {
    return this.http.post<SummaryInfo>('/myadmin-payment-services/services/rest/paymentservice/payment/applyNonTPPayment', paymentInfo);
  }

  processTpPayment(paymentInfo: TpPaymentInfo): Observable<SummaryInfo> {
    return this.http.post<SummaryInfo>('/myadmin-payment-services/services/rest/paymentservice/payment/applyTPPayment', paymentInfo);
  }
}
