import { Injectable } from '@angular/core';
import { StudyQuotationInfo } from '../info-objects';

@Injectable()
export class StudyFeeCriteriaService {
  searchCriteria: StudyQuotationInfo;

  constructor() {
    this.searchCriteria = new StudyQuotationInfo();
  }

}


