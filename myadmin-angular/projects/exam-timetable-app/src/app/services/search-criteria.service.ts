import {Injectable} from '@angular/core';
import {SearchCriteriaInfo} from '../info-objects/shared/search-criteria-info';

@Injectable()
export class SearchCriteriaService {

  /**
   * The currently active search criteria
   */
  searchCriteria: SearchCriteriaInfo = null;

  constructor() {
   }

}
