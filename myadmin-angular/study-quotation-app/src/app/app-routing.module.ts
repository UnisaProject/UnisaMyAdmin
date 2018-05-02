import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { StudyQuotationSearchComponent } from './components/study-quotation-search/study-quotation-search.component';
import { StudyQuotationResultComponent } from './components/study-quotation-result/study-quotation-result.component';

const routes: Routes = [
  { path: '', redirectTo: '/search', pathMatch: 'full'},
  { path: 'search', component: StudyQuotationSearchComponent},
  { path: 'result', component: StudyQuotationResultComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
