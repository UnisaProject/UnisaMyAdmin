import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ExamTimetableSearchComponent } from './components/exam-timetable-search/exam-timetable-search.component';
import { ExamTimetableResultComponent } from './components/exam-timetable-result/exam-timetable-result.component';

const routes: Routes = [
  { path: '', redirectTo: '/search', pathMatch: 'full'},
  { path: 'search', component: ExamTimetableSearchComponent},
  { path: 'result', component: ExamTimetableResultComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
