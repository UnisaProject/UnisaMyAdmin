import {Injectable} from '@angular/core';
import {CreditCardPaymentForm} from '../info-objects';
import {ApplicationPaymentInfo} from '../info-objects';
import {TpPaymentInfo} from '../info-objects';
import {NonTpPaymentInfo} from '../info-objects';

@Injectable()
export class CreditCardFormService {

  /**
   * The currently active form
   */
  creditCardPaymentForm: CreditCardPaymentForm = null;
  applicationPaymentInfo: ApplicationPaymentInfo = null;
  tpPaymentInfo: TpPaymentInfo = null;
  nonTpPaymentInfo: NonTpPaymentInfo = null;

  constructor() {
   }

}
