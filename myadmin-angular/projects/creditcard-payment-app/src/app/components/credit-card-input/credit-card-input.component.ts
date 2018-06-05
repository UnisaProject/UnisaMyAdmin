import {Component, Input, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

/**
 * Component to contain the credit card inputs
 */
@Component({
  selector: 'unisa-credit-card-input',
  templateUrl: './credit-card-input.component.html',
  styleUrls: ['./credit-card-input.component.scss']
})
export class CreditCardInputComponent implements OnInit {

  expiryYears: string[] = [];

  @Input("form")
  parentForm: FormGroup;

  creditCardForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder,) {
    this.setupExpiryYears();
    this.creditCardForm = this.formBuilder.group({
      libraryFineFeeAmountInput : [0, Validators.required],
      payMatricFirstAppFee : [false, Validators.required],
      studyFeeAmountInput : [0, Validators.required],

      // Credit card
      cnumber : [null, Validators.required],
      cardHolder : [null, Validators.required],
      budgetPeriod : ["0", Validators.required],
      cvvnumber : [null, Validators.required],
      expiryYear : [this.expiryYears[0], Validators.required],
      expiryMonth : ["01", Validators.required]
    });
  }

  ngOnInit() {
    this.parentForm.addControl("creditCard", this.creditCardForm);
  }


  private setupExpiryYears(){
    const currentYear = new Date().getFullYear();
    for(let i = 0 ; i <= 10 ; i++){
      this.expiryYears.push(`${currentYear+i}`);
    }
  }

}
