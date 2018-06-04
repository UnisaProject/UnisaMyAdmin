import { NgModule } from '@angular/core';
import {ToasterContainerComponent, ToasterModule, ToasterService} from "angular2-toaster";
import {ToasterNotificationService} from "./services/toaster-notification.service";
import {OrderByPipe} from "./pipes/orderby.pipe";
import {ShortDescriptionPipe} from "./pipes/short-description.pipe";
import {DescriptionPipe} from "./pipes/description.pipe";


@NgModule({
  imports: [
    ToasterModule,
  ],
  declarations: [
    OrderByPipe,
    ShortDescriptionPipe,
    DescriptionPipe,
  ],
  exports: [
    ToasterContainerComponent,
    ShortDescriptionPipe,
    DescriptionPipe,
  ],
  providers:[
    ToasterService,
    ToasterNotificationService,
  ]
})
export class MyadminLibModule { }
