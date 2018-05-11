import { Injectable } from '@angular/core';
import { StudyQuotationRequest } from '../info-objects';

@Injectable()
export class StudyFeeCriteriaService {
  searchCriteria: StudyQuotationRequest;

  constructor() {
    this.searchCriteria = new StudyQuotationRequest(new Date().getFullYear(), "1015", "02011", "00000", false, false, null);
  }

}


