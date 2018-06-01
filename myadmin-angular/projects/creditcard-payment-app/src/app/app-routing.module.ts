import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {PaymentSearchComponentComponent} from "./components/payment-search-component/payment-search-component.component";

const routes: Routes = [
  { path: '', redirectTo: '/search', pathMatch: 'full'},
  { path: 'search', component: PaymentSearchComponentComponent},];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
