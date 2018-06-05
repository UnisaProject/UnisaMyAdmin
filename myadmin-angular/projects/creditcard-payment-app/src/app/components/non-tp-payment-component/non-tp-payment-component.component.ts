import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {CreditCardPaymentForm} from "../../info-objects";
import {CreditCardFormService} from "../../services/creditcard-form.service";
import {Router} from "@angular/router";

@Component({
  selector: 'unisa-non-tp-payment-component',
  templateUrl: './non-tp-payment-component.component.html',
  styleUrls: ['./non-tp-payment-component.component.scss']
})
export class NonTpPaymentComponentComponent implements OnInit {

  nonTpForm: FormGroup;
  creditCardPaymentForm: CreditCardPaymentForm;

  constructor(private router:Router,
              private formBuilder: FormBuilder,
              private creditCardFormService: CreditCardFormService) {
    this.initForm();
  }

  private initForm(){
    this.nonTpForm = this.formBuilder.group({
      libraryFineFeeAmountInput : [0, Validators.required],
      payMatricFirstAppFee : [false, Validators.required],
      studyFeeAmountInput : [0, Validators.required],

      // Credit card
      cnumber : [null, Validators.required],
      cardHolder : [null, Validators.required],
      budgetPeriod : [null, Validators.required],
      cvvnumber : [null, Validators.required],
    });
  }

  ngOnInit() {
    this.creditCardPaymentForm = this.creditCardFormService.creditCardPaymentForm;
    if(this.creditCardPaymentForm === null ||  this.creditCardPaymentForm.studentNumber === null){
      this.router.navigateByUrl("/studentInput")
    }
    else {
      // TODO fake some data for now
      this.creditCardPaymentForm.studentName = "John Doe";
      this.creditCardPaymentForm.regStatusDescription = "Registered";
      this.creditCardPaymentForm.email = "test@mail.com";
      this.creditCardPaymentForm.creditDebitIndicator = "credit";
      this.creditCardPaymentForm.libCreditDebitIndicator = "debit";
      this.creditCardPaymentForm.libraryFineFee = 100.50;
      this.creditCardPaymentForm.balanceAmount = 250.00;
      this.nonTpForm.patchValue({...this.creditCardPaymentForm});
    }
  }

  onSubmit(){

  }

}
