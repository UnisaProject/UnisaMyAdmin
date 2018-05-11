import {Injectable, Injector} from '@angular/core';
import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
import {ToasterNotificationService} from '../services';
import {FrameworkError} from "../info-objects/shared/framework-error";

@Injectable()
export class HttpErrorInterceptor implements HttpInterceptor {

  constructor(private injector: Injector) { }

  intercept (req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const toaster = this.injector.get(ToasterNotificationService);
    return next.handle(req)
      .catch((response: HttpErrorResponse) => {
        if(response.error instanceof Error){
          toaster.error('Client Error', response.error.message);
        }
        // If it looks like a framework error
        else if(response.error && response.error.message){
          const frameworkError: FrameworkError = response.error;
          toaster.error('Unexpected Error', frameworkError.message);
        }
        else {
          toaster.error('Unexpected Error', response.message);
        }
        return Observable.throw(response);
      });
  }
}
