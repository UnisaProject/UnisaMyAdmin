import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {ParcelTrackingSearchComponent} from './components/parcel-tracking-search/parcel-tracking-search.component';
import {ParcelTrackingResultComponent} from './components/parcel-tracking-result/parcel-tracking-result.component';
import {ParcelTrackingUnknownuserComponent} from './components/parcel-tracking-unknownuser/parcel-tracking-unknownuser.component';

const routes:Routes = [
  {path: '', redirectTo: '/search', pathMatch: 'full'},
  {path: 'search', component: ParcelTrackingSearchComponent},
  {path: 'result/:id', component: ParcelTrackingResultComponent},
  {path: 'unknown', component: ParcelTrackingUnknownuserComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
