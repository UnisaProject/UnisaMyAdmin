import { Component } from '@angular/core';
import { ToasterConfig } from 'angular2-toaster';

@Component({
  selector: 'unisa-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'app';

  public toasterConfig: ToasterConfig = new ToasterConfig({
    animation: 'flyLeft',
    showCloseButton: true,
    timeout: 0,
    mouseoverTimerStop: false,
    positionClass: 'toast-top-right'
  });
}