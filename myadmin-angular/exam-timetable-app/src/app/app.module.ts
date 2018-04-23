import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { ExamTimetableSearchComponent } from './components/exam-timetable-search/exam-timetable-search.component';
import { ExamAdmissionService, ExamPeriodService, ExamPeriodDateService, SearchCriteriaService } from './services';
import { OrderByPipe } from './directives/orderby.pipe';
import { ExamTimetableResultComponent } from './components/exam-timetable-result/exam-timetable-result.component';
import {BlockUIModule} from "ng-block-ui";

@NgModule({
  declarations: [
    AppComponent,
    ExamTimetableSearchComponent,
    OrderByPipe,
    ExamTimetableResultComponent
  ],
  imports: [
    BrowserModule,
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
    SearchCriteriaService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
