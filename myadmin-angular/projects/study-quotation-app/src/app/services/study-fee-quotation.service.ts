import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {StudyFeeQuotationRequestInfo, StudyFeeQuotationInfo} from '../info-objects';

@Injectable()
export class StudyFeeQuotationService {

  searchCriteria: StudyFeeQuotationRequestInfo = null;

  constructor(private http:HttpClient) {  }

  submitStudyFeeQuotationRequest(studyFeeQuotationInfo: StudyFeeQuotationRequestInfo):Observable<StudyFeeQuotationInfo> {
    return this.http.post<StudyFeeQuotationInfo>('/myadmin-student-services/services/rest/studyfeequotationservice/quotationrequest/submission', studyFeeQuotationInfo);
  }
  
}
