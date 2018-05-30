import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ParcelTrackingInfo} from '../info-objects';

@Injectable()
export class ParcelTrackingService {

  constructor(private http:HttpClient) {
  }

  trackStudentParcel(userId:number):Observable<ParcelTrackingInfo> {
    return this.http.get<ParcelTrackingInfo>('rest/trackandtrace/' + userId);
  }

}
