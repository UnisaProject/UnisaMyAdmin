import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {ParcelTrackingSearchComponent} from './components/parcel-tracking-search/parcel-tracking-search.component';
import {ParcelTrackingResultComponent} from './components/parcel-tracking-result/parcel-tracking-result.component';

const routes:Routes = [
  {path: '', redirectTo: '/search', pathMatch: 'full'},
  {path: 'search', component: ParcelTrackingSearchComponent},
  {path: 'result/:id', component: ParcelTrackingResultComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
