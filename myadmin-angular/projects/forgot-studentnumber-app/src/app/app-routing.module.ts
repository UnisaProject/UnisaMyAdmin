import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ForgotStudentSearchComponent } from './components/forgot-student-search/forgot-student-search.component';
import { ForgotStudentResultComponent } from './components/forgot-student-result/forgot-student-result.component';

const routes: Routes = [
  { path: '', redirectTo: '/search', pathMatch: 'full'},
  { path: 'search', component: ForgotStudentSearchComponent},
  { path: 'result', component: ForgotStudentResultComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
