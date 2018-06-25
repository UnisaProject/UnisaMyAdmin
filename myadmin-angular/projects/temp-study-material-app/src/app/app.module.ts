import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import {BlockUIModule} from "ng-block-ui";
import { AppComponent } from './app.component';
import { MaterialStudentInputComponent } from './components/material-student-input/material-student-input.component';
import { StudentCourselistComponent } from './components/student-courselist/student-courselist.component';
import { ViewCourseMaterialComponent } from './components/view-course-material/view-course-material.component';
import {MyadminLibModule, HttpErrorInterceptor} from "myadmin-lib";

import { HTTP_INTERCEPTORS } from '@angular/common/http';
/** Http interceptor providers in outside-in order */
export const HttpInterceptorProviders = [
  { provide: HTTP_INTERCEPTORS, useClass: HttpErrorInterceptor, multi: true }
];

@NgModule({
  declarations: [
    AppComponent,
    MaterialStudentInputComponent,
    StudentCourselistComponent,
    ViewCourseMaterialComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MyadminLibModule,
    FormsModule,
    ReactiveFormsModule,
    BlockUIModule.forRoot()
  ],
  providers: [HttpInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
