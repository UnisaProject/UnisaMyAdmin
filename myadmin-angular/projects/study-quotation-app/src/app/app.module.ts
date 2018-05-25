import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {AppRoutingModule} from './app-routing.module';
import {BlockUIModule} from "ng-block-ui";
import {ClarityModule} from '@clr/angular';

import {AppComponent} from './app.component';
import {StudyQuotationSearchComponent} from './components/study-quotation-search/study-quotation-search.component';
import {StudyQuotationResultComponent} from './components/study-quotation-result/study-quotation-result.component';
import {StudyFeeCriteriaService, StudyFeeQuotationService} from './services';
import { StudyQuotationClosedComponent } from './components/study-quotation-closed/study-quotation-closed.component';

import '@clr/icons';
import '@clr/icons/shapes/all-shapes';

@NgModule({
  declarations: [
    AppComponent,
    StudyQuotationSearchComponent,
    StudyQuotationResultComponent,
    StudyQuotationClosedComponent
  ],
  imports: [
    AppRoutingModule,
    BrowserModule,
    ClarityModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    BlockUIModule.forRoot()
  ],
  providers: [StudyFeeCriteriaService, StudyFeeQuotationService],
  bootstrap: [AppComponent]
})
export class AppModule { }
