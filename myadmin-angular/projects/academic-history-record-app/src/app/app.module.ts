import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { BlockUIModule} from "ng-block-ui";
import { ClarityModule } from '@clr/angular';
import { FormsModule, ReactiveFormsModule} from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { AcadHistoryInputComponent } from './components/acad-history-input/acad-history-input.component';
import { DisplayQualificationsComponent } from './components/display-qualifications/display-qualifications.component';
import { DisplayQualModulesComponent } from './components/display-qual-modules/display-qual-modules.component';

import {AcademicRecordService} from "./services/academic-record.service";
import {AcademicRecordModuleService} from "./services/academic-record-module.service";
import {SearchCriteriaService} from "./services/search-criteria.service";

import '@webcomponents/custom-elements';
import '@clr/icons';
import '@clr/icons/shapes/all-shapes';

import { HTTP_INTERCEPTORS } from '@angular/common/http';
import {MyadminLibModule, HttpErrorInterceptor, StudentService} from "myadmin-lib";
/** Http interceptor providers in outside-in order */
export const HttpInterceptorProviders = [
  { provide: HTTP_INTERCEPTORS, useClass: HttpErrorInterceptor, multi: true }
];
@NgModule({
  declarations: [
    AppComponent,
    AcadHistoryInputComponent,
    DisplayQualificationsComponent,
    DisplayQualModulesComponent
  ],
  imports: [
    AppRoutingModule,
    BrowserModule,
    BrowserAnimationsModule,
    MyadminLibModule,
    ClarityModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    BlockUIModule.forRoot()
  ],
  providers: [
    SearchCriteriaService,
    AcademicRecordService,
    AcademicRecordModuleService,
    StudentService,
    HttpInterceptorProviders
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
