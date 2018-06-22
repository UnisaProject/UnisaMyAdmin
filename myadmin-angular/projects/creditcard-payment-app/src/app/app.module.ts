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
import { CurrencyPipe } from '@angular/common';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { QualInputComponent } from './components/qual-input/qual-input.component';
import { StudentInputComponent } from './components/student-input/student-input.component';
import { SummaryComponent } from './components/summary/summary.component';
import { ApplyPaymentComponent } from './components/apply-payment/apply-payment.component';
import { NonTpPaymentComponent } from './components/non-tp-payment/non-tp-payment.component';
import { TpPaymentComponent } from './components/tp-payment/tp-payment.component';
import { CreditCardFormService } from "./services/creditcard-form.service";
import { CreditCardPaymentService } from "./services/credit-card-payment.service";
import { CreditCardInputComponent } from './components/credit-card-input/credit-card-input.component';
import { CurrencyFormatterDirective } from './directives/currency-formatter.directive';
/** Http interceptor providers in outside-in order */
export const HttpInterceptorProviders = [
  { provide: HTTP_INTERCEPTORS, useClass: HttpErrorInterceptor, multi: true }
];


@NgModule({
  declarations: [
    AppComponent,
    QualInputComponent,
    StudentInputComponent,
    SummaryComponent,
    ApplyPaymentComponent,
    NonTpPaymentComponent,
    TpPaymentComponent,
    CreditCardInputComponent,
    CurrencyFormatterDirective,
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
    CurrencyPipe,
    CreditCardPaymentService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
