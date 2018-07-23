import {BrowserModule} from "@angular/platform-browser";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {NgModule} from "@angular/core";
import {NgbModule, NgbDateAdapter, NgbDateNativeAdapter} from "@ng-bootstrap/ng-bootstrap";
import {HttpClientModule, HTTP_INTERCEPTORS} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {AppRoutingModule} from "./app-routing.module";
import {AppComponent} from "./app.component";
import {ForgotStudentSearchComponent} from "./components/forgot-student-search/forgot-student-search.component";
import {ForgotStudentResultComponent} from "./components/forgot-student-result/forgot-student-result.component";
import {SearchCriteriaService, StudentService} from "./services";
import {BlockUIModule} from "ng-block-ui";
import {MyadminLibModule, HttpErrorInterceptor} from "myadmin-lib";
import {ClarityModule} from "@clr/angular";
import "@webcomponents/custom-elements";
import "@clr/icons";
import "@clr/icons/shapes/all-shapes";
/** Http interceptor providers in outside-in order */
export const HttpInterceptorProviders = [
  {provide: HTTP_INTERCEPTORS, useClass: HttpErrorInterceptor, multi: true}
];


@NgModule({
  declarations: [
    AppComponent,
    ForgotStudentSearchComponent,
    ForgotStudentResultComponent
  ],
  imports: [
    AppRoutingModule,
    BlockUIModule.forRoot(),
    BrowserModule,
    BrowserAnimationsModule,
    ClarityModule,
    FormsModule,
    HttpClientModule,
    MyadminLibModule,
    NgbModule.forRoot(),
    ReactiveFormsModule
  ],
  providers: [
    SearchCriteriaService,
    StudentService,
    HttpInterceptorProviders,
    {provide: NgbDateAdapter, useClass: NgbDateNativeAdapter}],
  bootstrap: [AppComponent]
})
export class AppModule { }
