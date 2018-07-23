import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {PackageInfo} from '../info-objects';

@Injectable()
export class TrackingService {

  constructor(private http:HttpClient) {
  }

  trackPackageByStudent(studentNumber:number):Observable<PackageInfo> {
    return this.http.get<PackageInfo>(`/myadmin-tracking-services/services/rest/postaltrackingservice/track/${studentNumber}`);
  }

}
