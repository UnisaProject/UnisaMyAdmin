import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule} from '@angular/forms';
import {ClarityModule} from '@clr/angular';

import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { ExamTimetableSearchComponent } from './components/exam-timetable-search/exam-timetable-search.component';
import { ExamAdmissionService, ExamPeriodService, ExamPeriodDateService, SearchCriteriaService, ToasterNotificationService } from './services';
import { OrderByPipe } from './directives/orderby.pipe';
import { ExamTimetableResultComponent } from './components/exam-timetable-result/exam-timetable-result.component';
import { BlockUIModule} from "ng-block-ui";
import { HttpInterceptorProviders } from './http-interceptors';
import { ToasterModule, ToasterService } from 'angular2-toaster';
import { DescriptionPipe } from './pipes/description.pipe';
import { ShortDescriptionPipe } from './pipes/short-description.pipe';

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
    ClarityModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    ToasterModule,
    BlockUIModule.forRoot()
  ],
  providers: [
    ExamAdmissionService,
    ExamPeriodService,
    ExamPeriodDateService,
    SearchCriteriaService,
    ToasterService,
    ToasterNotificationService,
    HttpInterceptorProviders,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
