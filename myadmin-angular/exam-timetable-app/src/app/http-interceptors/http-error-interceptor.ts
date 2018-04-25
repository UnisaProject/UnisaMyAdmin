import { Injectable, Injector } from '@angular/core';
import { HttpEvent, HttpInterceptor, HttpHandler, HttpRequest, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
import { ToasterNotificationService } from '../services/toaster-notification.service';

@Injectable()
export class HttpErrorInterceptor implements HttpInterceptor {

  constructor(private injector: Injector) { }

  intercept (req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    // console.log(req);
    const toaster = this.injector.get(ToasterNotificationService);

    return next.handle(req)
      // .do((ev: HttpEvent<any>) => {
      //   if (ev instanceof HttpResponse) {
      //     console.log('ev in the do: ', ev);
      //   }
      // })
      .catch((response: any) => {
        if (response instanceof HttpErrorResponse) {
          console.log('response in the catch: ', response);
          toaster.error('Unexpected Error', response.message);
        }

        return Observable.throw(response);
      });
  }
  // intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
  //   console.log(request);
  //   const toaster = this.injector.get(ToasterNotificationService);
  //
  //   return next.handle(request)
  //     .do((ev: HttpEvent<any>) => {
  //       if (ev instanceof HttpResponse) {
  //         console.log('ev in the do: ', ev);
  //       }
  //     })
  //     .catch((err: HttpErrorResponse) => {
  //
  //       if (err.error instanceof Error) {
  //         // A client-side or network error occurred. Handle it accordingly.
  //         console.error('An error occurred:', err.error.message);
  //       } else {
  //         // The backend returned an unsuccessful response code.
  //         // The response body may contain clues as to what went wrong,
  //     //    console.error(`Backend returned code ${err.status}, body was: ${err.error}`);
  //           console.error(`Backend returned code ${err.status}, ` + `body was: ${err.error}`);
  //       }
  //
  //       // ...optionally return a default fallback value so app can continue (pick one)
  //       // which could be a default value (which has to be a HttpResponse here)
  //       // return Observable.of(new HttpResponse({body: [{name: "Default value..."}]}));
  //       // or simply an empty observable
  //       return Observable.throw(err);
  //       //return Observable.empty<HttpEvent<any>>();
  //     });
  // }
}
