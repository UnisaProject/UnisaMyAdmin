import {ToasterConfig} from "angular2-toaster";

export const unisaToasterConfig = new ToasterConfig({
  animation: 'flyLeft',
  showCloseButton: true,
  timeout: 0,
  mouseoverTimerStop: false,
  positionClass: 'toast-top-right'
});
