/**
 * An interface defining the object that represents a framework error.
 */
export interface FrameworkError{

  /**
   * A message that can be displayed to the user.
   */
  message: string;

  /**
   * A numeric code for this error type.
   */
  errorCode: number;
}
