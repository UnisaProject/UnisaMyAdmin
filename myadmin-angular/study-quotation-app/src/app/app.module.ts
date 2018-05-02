import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule} from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { BlockUIModule} from "ng-block-ui";

import { AppComponent } from './app.component';
import { StudyQuotationSearchComponent } from './components/study-quotation-search/study-quotation-search.component';
import { StudyQuotationResultComponent } from './components/study-quotation-result/study-quotation-result.component';


@NgModule({
  declarations: [
    AppComponent,
    StudyQuotationSearchComponent,
    StudyQuotationResultComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    BlockUIModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
