import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {AppRoutingModule} from './app-routing.module';
import {BlockUIModule} from "ng-block-ui";

import {AppComponent} from './app.component';
import {StudyQuotationSearchComponent} from './components/study-quotation-search/study-quotation-search.component';
import {StudyQuotationResultComponent} from './components/study-quotation-result/study-quotation-result.component';
import {StudyFeeCriteriaService, StudyFeeQuotationService} from './services';

@NgModule({
  declarations: [
    AppComponent,
    StudyQuotationSearchComponent,
    StudyQuotationResultComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    BlockUIModule.forRoot()
  ],
  providers: [StudyFeeCriteriaService, StudyFeeQuotationService],
  bootstrap: [AppComponent]
})
export class AppModule { }
