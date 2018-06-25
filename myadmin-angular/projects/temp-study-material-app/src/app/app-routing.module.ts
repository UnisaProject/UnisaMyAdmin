import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MaterialStudentInputComponent } from './components/material-student-input/material-student-input.component';
import { StudentCourselistComponent } from './components/student-courselist/student-courselist.component';
import { ViewCourseMaterialComponent } from './components/view-course-material/view-course-material.component';

const routes: Routes = [
  { path: '', redirectTo: '/search', pathMatch: 'full'},
  { path: 'search', component: MaterialStudentInputComponent},
  { path: 'courses', component: StudentCourselistComponent},
  { path: 'viewCourse', component: ViewCourseMaterialComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
