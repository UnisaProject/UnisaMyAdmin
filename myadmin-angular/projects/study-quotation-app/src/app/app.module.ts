import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {AppRoutingModule} from './app-routing.module';
import {BlockUIModule} from "ng-block-ui";
import {ClarityModule} from '@clr/angular';

import {AppComponent} from './app.component';
import {StudyQuotationSearchComponent} from './components/study-quotation-search/study-quotation-search.component';
import {StudyQuotationResultComponent} from './components/study-quotation-result/study-quotation-result.component';
import {RegistrationPeriodService, StudyFeeQuotationService} from './services';
import { StudyQuotationClosedComponent } from './components/study-quotation-closed/study-quotation-closed.component';
import {MyadminLibModule, HttpErrorInterceptor} from "myadmin-lib";

import { HTTP_INTERCEPTORS } from '@angular/common/http';
/** Http interceptor providers in outside-in order */
export const HttpInterceptorProviders = [
  { provide: HTTP_INTERCEPTORS, useClass: HttpErrorInterceptor, multi: true }
];



import '@webcomponents/custom-elements';
import '@clr/icons';
import '@clr/icons/shapes/all-shapes';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";

@NgModule({
  declarations: [
    AppComponent,
    StudyQuotationSearchComponent,
    StudyQuotationResultComponent,
    StudyQuotationClosedComponent
  ],
  imports: [
    AppRoutingModule,
    BrowserModule,
    BrowserAnimationsModule,
    ClarityModule,
    MyadminLibModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    BlockUIModule.forRoot()
  ],
  providers: [
    RegistrationPeriodService,
    StudyFeeQuotationService,
    HttpInterceptorProviders
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
