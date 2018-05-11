import {Injectable} from '@angular/core';
import {StudyQuotationRequest} from '../info-objects';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";

@Injectable()
export class StudyFeeCriteriaService {
  searchCriteria: StudyQuotationRequest = null;

  constructor(private http:HttpClient) {
  }

  getQuotationYear():Observable<number> {
       return this.http.get<number>('rest/studyfeequotation/quotationYear');
  }
}


