export interface CreditCardPaymentForm{

  /**
   * Student number
   */
  studentNumber: string;

  /**
   * Name of the student
   */
  studentName?:string;

  /**
   * Email address to send a receipt to.
   */
  email?:string;

  /**
   * Library fine fee
   */
  libraryFineFee?:number;

  /**
   * Library credit or debit indicator.
   */
  libCreditDebitIndicator?:string;

  /**
   * Balance amount for the account.
   */
  balanceAmount?: number;

  /**
   * Indicator of the account is in credit or debit
   */
  creditDebitIndicator?:string;

  // Qualification object in java
  /**
   * Qualification description
   */
  qualDesc?: string;

  /**
   * Qualification code
   */
  qualCode?: string;

  /**
   * Registration status description
   */
  regStatusDescription?:string;

  /**
   * Flag if the user wishes to pay for matriculation
   */
  payMatricFirstAppFee?: boolean;
}
