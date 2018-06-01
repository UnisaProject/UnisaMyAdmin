import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {StudentInputComponentComponent} from "./components/student-input-component/student-input-component.component";
import {QualInputComponentComponent} from "./components/qual-input-component/qual-input-component.component";
import {ApplyPaymentComponentComponent} from "./components/apply-payment-component/apply-payment-component.component";
import {TpPaymentComponentComponent} from "./components/tp-payment-component/tp-payment-component.component";
import {NonTpPaymentComponentComponent} from "./components/non-tp-payment-component/non-tp-payment-component.component";
import {SummaryComponentComponent} from "./components/summary-component/summary-component.component";

const routes: Routes = [
  { path: '', redirectTo: '/qualInput', pathMatch: 'full'},
  { path: 'studentInput', component: StudentInputComponentComponent},
  { path: 'qualInput', component: QualInputComponentComponent},
  { path: 'applyPayment', component: ApplyPaymentComponentComponent},
  { path: 'tpPayment', component: TpPaymentComponentComponent},
  { path: 'nonTpPayment', component: NonTpPaymentComponentComponent},
  { path: 'summary', component: SummaryComponentComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
