import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {HttpErrorInterceptor, MyadminLibModule} from "myadmin-lib";
import {AppComponent} from "./app.component";
import {AppRoutingModule} from "./app-routing.module";
import { ViewExamPaperMaterialComponent } from './components/view-exam-paper-material/view-exam-paper-material.component';
import { InputModuleComponent } from './components/input-module/input-module.component';
import {BlockUIModule} from "ng-block-ui";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {ClarityModule} from "@clr/angular";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";

import '@webcomponents/custom-elements';
import '@clr/icons';
import '@clr/icons/shapes/all-shapes';
/** Http interceptor providers in outside-in order */
export const HttpInterceptorProviders = [
  {provide: HTTP_INTERCEPTORS, useClass: HttpErrorInterceptor, multi: true}
];

@NgModule({
  declarations: [
    AppComponent,
    ViewExamPaperMaterialComponent,
    InputModuleComponent
  ],
  imports: [
    BrowserModule,
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
  providers: [
    HttpInterceptorProviders,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
