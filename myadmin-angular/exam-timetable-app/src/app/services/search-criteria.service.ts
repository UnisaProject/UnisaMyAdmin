import { Injectable } from '@angular/core';
import { SearchCriteriaInfo } from './../info-objects/shared/search-criteria-info';
import { ExamPeriodInfo } from '../info-objects';

@Injectable() 
export class SearchCriteriaService {
  searchCriteria: SearchCriteriaInfo;

  constructor() {
    this.searchCriteria = new SearchCriteriaInfo(new ExamPeriodInfo());
   }

}