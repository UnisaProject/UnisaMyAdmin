import {StudyUnitInfo} from './';
import {StudyQuotationRequestInfo} from "./study-quotation-request-info";

/**
 * An interface representing an object that defines a Study quotation
 */
export interface StudyQuotationInfo extends StudyQuotationRequestInfo {

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
  studyUnitInfos:StudyUnitInfo[];

  /**
   * Validation message from coolgen
   */
  coolgenErrorMsg?:string;
}
