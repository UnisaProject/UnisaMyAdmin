/**
 * An interface the defines an object that represents the request details
 * for a study fee quotation.
 */
export interface StudyQuotationRequest {

  /**
   * Academic year to request a quotation for
   */
  academicYear:number;

  /**
   * Country code of study
   */
  countryCode:string;

  /**
   * Qualification type.
   */
  qualification:string;

  /**
   * Qualification code.
   */
  qualificationCode:string;

  /**
   * Flag if a library card is required
   */
  libraryCard:boolean;

  /**
   * Flag if matric exception is required.
   */
  matricExemption:boolean;

  /**
   * List of course codes to get a quotation for.
   */
  courseCodes:string[];
}
