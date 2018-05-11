import {StudyUnitInfo} from './';
import {StudyQuotationRequest} from "./study-quotation-request";

/**
 * An interface representing an object that defines a Study quotation
 */
export interface StudyQuotation extends StudyQuotationRequest {

  /**
   * Fee for the registration.
   */
  registrationFee:string;

  /**
   * Cost for the library card.
   */
  libraryCardCost:number;

  /**
   * Cost for matric excemption.
   */
  matricExemptionCost:number;

  /**
   * Number of prescribed books.
   */
  prescribedBooks:number;

  /**
   * Levy for Foreign students
   */
  foreignLevy:number;

  /**
   * Total fee
   */
  totalFee:number;

  /**
   * Payment due at registration
   */
  paymentDue:number;

  /**
   * Information for the study units
   */
  studyUnits:StudyUnitInfo[];
}
