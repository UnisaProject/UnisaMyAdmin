import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AcadHistoryInputComponent } from './components/acad-history-input/acad-history-input.component';
import { DisplayQualificationsComponent } from './components/display-qualifications/display-qualifications.component';
import { DisplayQualModulesComponent } from './components/display-qual-modules/display-qual-modules.component';

const routes: Routes = [
  { path: '', redirectTo: '/acadHistoryInput', pathMatch: 'full'},
  { path: 'acadHistoryInput', component:AcadHistoryInputComponent},
  { path: 'academicRecord', component: DisplayQualificationsComponent},
  { path: 'academicRecordModules', component: DisplayQualModulesComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
