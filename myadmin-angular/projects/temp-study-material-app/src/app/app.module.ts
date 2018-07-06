import {BrowserModule} from "@angular/platform-browser";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {NgbModule,NgbDateAdapter, NgbDateNativeAdapter} from '@ng-bootstrap/ng-bootstrap';
import {NgModule} from "@angular/core";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule, HTTP_INTERCEPTORS} from "@angular/common/http";
import {AppRoutingModule} from "./app-routing.module";
import {BlockUIModule} from "ng-block-ui";
import { ClarityModule } from '@clr/angular';
import {AppComponent} from "./app.component";
import {MaterialStudentInputComponent} from "./components/material-student-input/material-student-input.component";
import {StudentCourselistComponent} from "./components/student-courselist/student-courselist.component";
import {ViewCourseMaterialComponent} from "./components/view-course-material/view-course-material.component";
import {MyadminLibModule, HttpErrorInterceptor} from "myadmin-lib";
import {StudyMaterialFormService, StudyMaterialService} from "./services";
/** Http interceptor providers in outside-in order */
export const HttpInterceptorProviders = [
  {provide: HTTP_INTERCEPTORS, useClass: HttpErrorInterceptor, multi: true}
];

import '@webcomponents/custom-elements';
import '@clr/icons';
import '@clr/icons/shapes/all-shapes';

@NgModule({
  declarations: [
    AppComponent,
    MaterialStudentInputComponent,
    StudentCourselistComponent,
    ViewCourseMaterialComponent
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
    BlockUIModule.forRoot(),
    NgbModule.forRoot()
  ],
  providers: [StudyMaterialFormService,
    StudyMaterialService,
    HttpInterceptorProviders,
    {provide: NgbDateAdapter, useClass: NgbDateNativeAdapter}],
  bootstrap: [AppComponent]
})
export class AppModule {
}
