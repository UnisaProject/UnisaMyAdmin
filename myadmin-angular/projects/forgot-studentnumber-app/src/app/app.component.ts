import { Component } from '@angular/core';
import { ToasterConfig } from 'angular2-toaster';
import {unisaToasterConfig} from "myadmin-lib";

@Component({
  selector: 'unisa-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  toasterConfig: ToasterConfig = unisaToasterConfig;
}
