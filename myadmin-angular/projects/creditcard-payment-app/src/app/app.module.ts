import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule} from '@angular/forms';
import {ClarityModule} from '@clr/angular';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BlockUIModule} from "ng-block-ui";

import '@webcomponents/custom-elements';
import '@clr/icons';
import '@clr/icons/shapes/all-shapes';
import {MyadminLibModule, HttpErrorInterceptor} from "myadmin-lib";

import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { PaymentSearchComponentComponent } from './components/payment-search-component/payment-search-component.component';
/** Http interceptor providers in outside-in order */
export const HttpInterceptorProviders = [
  { provide: HTTP_INTERCEPTORS, useClass: HttpErrorInterceptor, multi: true }
];


@NgModule({
  declarations: [
    AppComponent,
    PaymentSearchComponentComponent,
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
    HttpInterceptorProviders
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }