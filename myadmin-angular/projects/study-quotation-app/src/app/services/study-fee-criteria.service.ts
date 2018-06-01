import {Injectable} from '@angular/core';
import {StudyQuotationRequestInfo} from '../info-objects';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable()
export class StudyFeeCriteriaService {
  searchCriteria: StudyQuotationRequestInfo = null;

  constructor(private http:HttpClient) {
  }

  getQuotationYear():Observable<number> {
       return this.http.get<number>('/myadmin-student-services/rest/studyfeequotation/quotationYear');
  }
}


