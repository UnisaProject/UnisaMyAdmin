import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {BlockUI, NgBlockUI} from "ng-block-ui";
import {CreditCardPaymentForm} from "../../info-objects";
import {CreditCardFormService} from "../../services/creditcard-form.service";
import {Router} from "@angular/router";

@Component({
  selector: 'unisa-tp-payment',
  templateUrl: './tp-payment.component.html',
  styleUrls: ['./tp-payment.component.scss']
})
export class TpPaymentComponent implements OnInit {

  tpForm: FormGroup;
  creditCardPaymentForm: CreditCardPaymentForm;


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
    this.tpForm = this.formBuilder.group({
      dueImmediately : [0, Validators.required],
      minimumForReg : [0, Validators.required],
      fullAccount : [0, Validators.required],

      libraryFineFeeAmountInput : [0, Validators.required],
      payMatricFirstAppFee : [false, Validators.required],
      studyFeeAmountInput : [0, Validators.required],
      ccTotalAmountInput : [0, Validators.required]
    });
  }


  ngOnInit() {
    this.blockUI.stop();
    this.creditCardPaymentForm = this.creditCardFormService.creditCardPaymentForm;
    if(this.creditCardPaymentForm === null ||  this.creditCardPaymentForm.studentInfo === null || this.creditCardPaymentForm.studentInfo.studentNumber === null){
      this.router.navigateByUrl("/studentInput")
    }
    else {
      this.tpForm.patchValue({...this.creditCardPaymentForm});
    }
  }

  back(){
    this.tpForm.reset();
    this.creditCardFormService.creditCardPaymentForm.creditCardInfo = null;
  }

  close(){
    this.tpForm.reset();
    this.creditCardFormService.creditCardPaymentForm = null;
    this.router.navigateByUrl("/studentInput")
  }

  cancel(){
    this.tpForm.reset();
    this.creditCardFormService.creditCardPaymentForm = null;
    this.router.navigateByUrl("/studentInput")
  }

  payNow(){

  }

}
