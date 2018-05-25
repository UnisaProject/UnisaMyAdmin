/**
 * An interface defining the object that represents a application error.
 */
export interface ErrorInfo{

  /**
   * A message that can be displayed to the user.
   */
  message: string;

  /**
   * A numeric code for this error type.
   */
  errorCode: number;
}
