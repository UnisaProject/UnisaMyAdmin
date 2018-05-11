import {Injectable} from '@angular/core';
import {StudyQuotationRequest} from '../info-objects';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";

@Injectable()
export class StudyFeeCriteriaService {
  searchCriteria: StudyQuotationRequest;

  constructor(private http:HttpClient) {
    this.searchCriteria = new StudyQuotationRequest();
  constructor() {
    this.searchCriteria = new StudyQuotationRequest(new Date().getFullYear(), "1015", "02011", "00000", false, false, null);
  }

  getQuotationYear():Observable<number> {
       return this.http.get<number>('rest/studyfeequotation/quotationYear');
  }
}


