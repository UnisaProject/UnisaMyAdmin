import {Injectable, Injector} from "@angular/core";
import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {catchError} from "rxjs/operators";
import {ToasterNotificationService} from "../services/toaster-notification.service";
import {ErrorInfo} from "../info-objects/error-info";

@Injectable()
export class HttpErrorInterceptor implements HttpInterceptor {

  constructor(private injector:Injector) {
  }

  intercept(req:HttpRequest<any>, next:HttpHandler):Observable<HttpEvent<any>> {
    const toaster = this.injector.get(ToasterNotificationService);
    return next.handle(req).pipe(
      catchError((response:HttpErrorResponse) => {
        if (response.error instanceof Error) {
          toaster.error('Client Error', response.error.message);
        }
        // If it looks like a framework error
        else if (response.error && response.error.message) {
          const errorInfo:ErrorInfo = response.error;
          if (errorInfo.errors) {
            for (let entry of errorInfo.errors) {
              toaster.error('Validation Error', entry);
            }
          } else {
            toaster.error('Unexpected Error', errorInfo.message);
          }
        }
        else {
          toaster.error('Unexpected Error', response.message);
        }
        return throwError(response);
      })
    );
  }
}
