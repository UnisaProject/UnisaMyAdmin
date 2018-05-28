import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule} from '@angular/forms';
import {ClarityModule} from '@clr/angular';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ExamTimetableSearchComponent } from './components/exam-timetable-search/exam-timetable-search.component';
import { ExamAdmissionService, ExamPeriodService, ExamPeriodDateService, SearchCriteriaService} from './services';
import { OrderByPipe } from './directives/orderby.pipe';
import { ExamTimetableResultComponent } from './components/exam-timetable-result/exam-timetable-result.component';
import { BlockUIModule} from "ng-block-ui";
import { DescriptionPipe } from './pipes/description.pipe';
import { ShortDescriptionPipe } from './pipes/short-description.pipe';

import '@webcomponents/custom-elements';
import '@clr/icons';
import '@clr/icons/shapes/all-shapes';
import {MyadminLibModule, HttpErrorInterceptor} from "myadmin-lib";

import { HTTP_INTERCEPTORS } from '@angular/common/http';
/** Http interceptor providers in outside-in order */
export const HttpInterceptorProviders = [
  { provide: HTTP_INTERCEPTORS, useClass: HttpErrorInterceptor, multi: true }
];


@NgModule({
  declarations: [
    AppComponent,
    ExamTimetableSearchComponent,
    OrderByPipe,
    ExamTimetableResultComponent,
    DescriptionPipe,
    ShortDescriptionPipe
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
    ExamAdmissionService,
    ExamPeriodService,
    ExamPeriodDateService,
    SearchCriteriaService,
    HttpInterceptorProviders
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
