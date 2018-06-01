import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {StudyQuotationRequestInfo} from '../info-objects';
import {StudyQuotationInfo} from "../info-objects";

@Injectable()
export class StudyFeeQuotationService {

  constructor(private http:HttpClient) {  }

  calculateStudyQuotation(studyQuotationInfo:StudyQuotationRequestInfo):Observable<StudyQuotationInfo> {
    const params = new HttpParams()
      .set('academicYear', <string><any>studyQuotationInfo.academicYear)
      .set('countryCode', <string><any>studyQuotationInfo.countryCode)
      .set('qualificationType', <string><any>studyQuotationInfo.qualification)
      .set('qualificationCode', <string><any>studyQuotationInfo.qualificationCode)
      .set('libraryCard', <string><any>studyQuotationInfo.libraryCard)
      .set('matricExemption', <string><any>studyQuotationInfo.matricExemption)
      .set('courseCodes', studyQuotationInfo.courseCodes.join(','));

    return this.http.get<StudyQuotationInfo>('/myadmin-student-services/rest/studyfeequotation/calculateQuotation', {params});
  }

}
