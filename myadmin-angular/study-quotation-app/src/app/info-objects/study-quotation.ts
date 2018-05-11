import {StudyUnitInfo} from './';
import {StudyQuotationRequest} from "./study-quotation-request";

export interface StudyQuotation extends StudyQuotationRequest{

  studyUnits:StudyUnitInfo[];
}
