import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {InputModuleComponent} from "./components/input-module/input-module.component";
import {ViewExamPaperMaterialComponent} from "./components/view-exam-paper-material/view-exam-paper-material.component";

const routes: Routes = [
  { path: '', redirectTo: '/inputModule', pathMatch: 'full'},
  { path: 'inputModule', component: InputModuleComponent},
  { path: 'examPapers/:moduleCode', component: ViewExamPaperMaterialComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
