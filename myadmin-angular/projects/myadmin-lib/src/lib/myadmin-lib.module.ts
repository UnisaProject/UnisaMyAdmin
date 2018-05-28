import { NgModule } from '@angular/core';
import { MyadminLibComponent } from './myadmin-lib.component';
import {ToasterContainerComponent, ToasterModule, ToasterService} from "angular2-toaster";
import {ToasterNotificationService} from "./services/toaster-notification.service";

@NgModule({
  imports: [
    ToasterModule,
  ],
  declarations: [
    MyadminLibComponent
  ],
  exports: [
    MyadminLibComponent,
    ToasterContainerComponent,

  ],
  providers:[
    ToasterService,
    ToasterNotificationService,
  ]
})
export class MyadminLibModule { }
