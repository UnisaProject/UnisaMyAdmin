import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {NgModule} from '@angular/core';
import {BlockUIModule} from "ng-block-ui";
import {HttpClientModule} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {MyadminLibModule, HttpErrorInterceptor} from "myadmin-lib";

import {HTTP_INTERCEPTORS} from '@angular/common/http';
import {ParcelTrackingSearchComponent} from './components/parcel-tracking-search/parcel-tracking-search.component';
import {ParcelTrackingResultComponent} from './components/parcel-tracking-result/parcel-tracking-result.component';
import {TrackingService} from './services/tracking.service';
import { ParcelTrackingUnknownuserComponent } from './components/parcel-tracking-unknownuser/parcel-tracking-unknownuser.component';
/** Http interceptor providers in outside-in order */
export const HttpInterceptorProviders = [
  {provide: HTTP_INTERCEPTORS, useClass: HttpErrorInterceptor, multi: true}
];

@NgModule({
  declarations: [
    AppComponent,
    ParcelTrackingSearchComponent,
    ParcelTrackingResultComponent,
    ParcelTrackingUnknownuserComponent
  ],
  imports: [
    AppRoutingModule,
    BrowserModule,
    BlockUIModule.forRoot(),
    BrowserAnimationsModule,
    MyadminLibModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [
    TrackingService,
    HttpInterceptorProviders
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
