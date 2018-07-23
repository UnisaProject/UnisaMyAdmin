import {NgModule, InjectionToken} from "@angular/core";
import {Routes, RouterModule} from "@angular/router";
import {StudentInputComponent} from "./components/student-input/student-input.component";
import {QualInputComponent} from "./components/qual-input/qual-input.component";
import {ApplyPaymentComponent} from "./components/apply-payment/apply-payment.component";
import {TpPaymentComponent} from "./components/tp-payment/tp-payment.component";
import {NonTpPaymentComponent} from "./components/non-tp-payment/non-tp-payment.component";
import {SummaryComponent} from "./components/summary/summary.component";
import {ActivatedRouteSnapshot} from "@angular/router";

const externalUrlProvider = new InjectionToken('externalUrlRedirectResolver');

const routes:Routes = [
  {path: '', redirectTo: '/studentInput', pathMatch: 'full'},
  {path: 'studentInput', component: StudentInputComponent},
  {path: 'qualInput', component: QualInputComponent},
  {path: 'applyPayment', component: ApplyPaymentComponent},
  {path: 'tpPayment', component: TpPaymentComponent},
  {path: 'nonTpPayment', component: NonTpPaymentComponent},
  {path: 'summary', component: SummaryComponent},
  {
    path: 'externalRedirect',
    resolve: {
      url: externalUrlProvider,
    },
    // We need a component here because we cannot define the route otherwise
    component: SummaryComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule],
  providers: [{
    provide: externalUrlProvider,
    useValue: (route:ActivatedRouteSnapshot) => {
      const externalUrl = "http://www.unisa.ac.za";
      window.open(externalUrl, '_self');
    },
  }]
})
export class AppRoutingModule {
}
