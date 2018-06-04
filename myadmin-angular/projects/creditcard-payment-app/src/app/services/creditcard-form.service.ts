import {Injectable} from '@angular/core';
import {CreditCardPaymentForm} from '../info-objects';

@Injectable()
export class CreditCardFormService {

  /**
   * The currently active form
   */
  creditCardPaymentForm: CreditCardPaymentForm = null;

  constructor() {
   }

}
