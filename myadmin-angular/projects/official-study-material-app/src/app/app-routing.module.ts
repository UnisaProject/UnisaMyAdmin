import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {InputCourseComponent} from "./components/input-course/input-course.component";
import {ViewCourseMaterialComponent} from "./components/view-course-material/view-course-material.component";

const routes: Routes = [
  { path: '', redirectTo: '/inputCourse', pathMatch: 'full'},
  { path: 'inputCourse', component: InputCourseComponent},
  { path: 'studyMaterial/:siteId', component: ViewCourseMaterialComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
