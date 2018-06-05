import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {StudentInputComponent} from "./components/student-input/student-input.component";
import {QualInputComponent} from "./components/qual-input/qual-input.component";
import {ApplyPaymentComponent} from "./components/apply-payment/apply-payment.component";
import {TpPaymentComponent} from "./components/tp-payment/tp-payment.component";
import {NonTpPaymentComponent} from "./components/non-tp-payment/non-tp-payment.component";
import {SummaryComponent} from "./components/summary/summary.component";

const routes: Routes = [
  { path: '', redirectTo: '/studentInput', pathMatch: 'full'},
  { path: 'studentInput', component: StudentInputComponent},
  { path: 'qualInput', component: QualInputComponent},
  { path: 'applyPayment', component: ApplyPaymentComponent},
  { path: 'tpPayment', component: TpPaymentComponent},
  { path: 'nonTpPayment', component: NonTpPaymentComponent},
  { path: 'summary', component: SummaryComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
