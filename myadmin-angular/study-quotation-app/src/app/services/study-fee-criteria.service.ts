import { Injectable } from '@angular/core';
import { StudyQuotationRequest } from '../info-objects';

@Injectable()
export class StudyFeeCriteriaService {
  searchCriteria: StudyQuotationRequest;

  constructor() {
    this.searchCriteria = new StudyQuotationRequest();
  }

}


