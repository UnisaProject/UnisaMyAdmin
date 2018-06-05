import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule} from '@angular/forms';
import { ClarityModule } from '@clr/angular';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BlockUIModule} from "ng-block-ui";

import '@webcomponents/custom-elements';
import '@clr/icons';
import '@clr/icons/shapes/all-shapes';
import {MyadminLibModule, HttpErrorInterceptor} from "myadmin-lib";

import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { QualInputComponentComponent } from './components/qual-input-component/qual-input-component.component';
import { StudentInputComponentComponent } from './components/student-input-component/student-input-component.component';
import { SummaryComponentComponent } from './components/summary-component/summary-component.component';
import { ApplyPaymentComponentComponent } from './components/apply-payment-component/apply-payment-component.component';
import { NonTpPaymentComponentComponent } from './components/non-tp-payment-component/non-tp-payment-component.component';
import { TpPaymentComponentComponent } from './components/tp-payment-component/tp-payment-component.component';
import { CreditCardFormService } from "./services/creditcard-form.service";
import { CreditCardPaymentService } from "./services/credit-card-payment.service";
/** Http interceptor providers in outside-in order */
export const HttpInterceptorProviders = [
  { provide: HTTP_INTERCEPTORS, useClass: HttpErrorInterceptor, multi: true }
];


@NgModule({
  declarations: [
    AppComponent,
    QualInputComponentComponent,
    StudentInputComponentComponent,
    SummaryComponentComponent,
    ApplyPaymentComponentComponent,
    NonTpPaymentComponentComponent,
    TpPaymentComponentComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MyadminLibModule,
    ClarityModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    BlockUIModule.forRoot()
  ],
  providers: [
    HttpInterceptorProviders,
    CreditCardFormService,
    CreditCardPaymentService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
