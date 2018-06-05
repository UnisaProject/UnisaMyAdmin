import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {CreditCardPaymentForm} from "../../info-objects";
import {CreditCardFormService} from "../../services/creditcard-form.service";
import {Router} from "@angular/router";
import {BlockUI, NgBlockUI} from "ng-block-ui";

@Component({
  selector: 'unisa-non-tp-payment',
  templateUrl: './non-tp-payment.component.html',
  styleUrls: ['./non-tp-payment.component.scss']
})
export class NonTpPaymentComponent implements OnInit {

  nonTpForm: FormGroup;
  creditCardPaymentForm: CreditCardPaymentForm;
  expiryYears: string[] = [];


  /**
   * Reference to blockUI
   */
  @BlockUI()
  private blockUI: NgBlockUI;

  constructor(private router:Router,
              private formBuilder: FormBuilder,
              private creditCardFormService: CreditCardFormService) {
    this.initForm();
  }

  private initForm(){
    this.setupExpiryYears();

    this.nonTpForm = this.formBuilder.group({
      libraryFineFeeAmountInput : [0, Validators.required],
      payMatricFirstAppFee : [false, Validators.required],
      studyFeeAmountInput : [0, Validators.required],

      // Credit card
      cnumber : [null, Validators.required],
      cardHolder : [null, Validators.required],
      budgetPeriod : ["0", Validators.required],
      cvvnumber : [null, Validators.required],
      expiryYear : [this.expiryYears[0], Validators.required],
      expiryMonth : ["01", Validators.required],

      ccTotalAmountInput : [0, Validators.required],
    });
  }

  private setupExpiryYears(){
    const currentYear = new Date().getFullYear();
    for(let i = 0 ; i <= 10 ; i++){
      this.expiryYears.push(`${currentYear+i}`);
    }
  }

  ngOnInit() {
    this.blockUI.stop();
    this.creditCardPaymentForm = this.creditCardFormService.creditCardPaymentForm;
    if(this.creditCardPaymentForm === null ||  this.creditCardPaymentForm.studentInfo === null || this.creditCardPaymentForm.studentInfo.studentNumber === null){
      this.router.navigateByUrl("/studentInput")
    }
    else {
      this.nonTpForm.patchValue({...this.creditCardPaymentForm});
    }
  }

  onSubmit(){

  }

}
