import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {InputCourseComponent} from "./components/input-course/input-course.component";
import {ViewExamMaterialComponent} from "./components/view-exam-material/view-exam-material.component";

const routes: Routes = [
  { path: '', redirectTo: '/inputCourse', pathMatch: 'full'},
  { path: 'inputCourse', component: InputCourseComponent},
  { path: 'examPapers/:courseCode', component: ViewExamMaterialComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
