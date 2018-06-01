import { NgModule } from '@angular/core';
import { MyadminLibComponent } from './myadmin-lib.component';
import {ToasterContainerComponent, ToasterModule, ToasterService} from "angular2-toaster";
import {ToasterNotificationService} from "./services/toaster-notification.service";
import {DescriptionPipe, ShortDescriptionPipe, OrderByPipe} from "./pipes";

@NgModule({
  imports: [
    ToasterModule,
  ],
  declarations: [
    MyadminLibComponent,
    OrderByPipe,
    ShortDescriptionPipe,
    DescriptionPipe,
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
