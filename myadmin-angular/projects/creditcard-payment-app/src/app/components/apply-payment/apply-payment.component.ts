import { Component, OnInit } from '@angular/core';
import {BlockUI, NgBlockUI} from "ng-block-ui";
import {FormBuilder, FormGroup, ValidationErrors, Validators} from "@angular/forms";
import {CreditCardPaymentForm} from "../../info-objects";
import {CreditCardPaymentService} from "../../services/credit-card-payment.service";
import {CreditCardFormService} from "../../services/creditcard-form.service";
import {Router} from "@angular/router";

@Component({
  selector: 'unisa-apply-payment',
  templateUrl: './apply-payment.component.html',
  styleUrls: ['./apply-payment.component.scss']
})
export class ApplyPaymentComponent implements OnInit {

  applyForm: FormGroup;

  creditCardPaymentForm: CreditCardPaymentForm;

  /**
   * Reference to blockUI
   */
  @BlockUI()
  private blockUI: NgBlockUI;

  constructor(private router:Router,
              private formBuilder: FormBuilder,
              private creditCardFormService: CreditCardFormService,
              private creditCardPaymentService: CreditCardPaymentService) {
    this.initForm();
  }

  private initForm(){
    this.applyForm = this.formBuilder.group({
      applyAmount : [0, Validators.required],
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
      this.applyForm.patchValue({...this.creditCardPaymentForm});
    }
  }

  back(){
    this.applyForm.reset();
    this.creditCardFormService.creditCardPaymentForm.creditCardInfo = null;
  }

  close(){
    this.applyForm.reset();
    this.creditCardFormService.creditCardPaymentForm = null;
    this.router.navigateByUrl("/studentInput")
  }

  cancel(){
    this.applyForm.reset();
    this.creditCardFormService.creditCardPaymentForm = null;
    this.router.navigateByUrl("/studentInput")
  }

  payNow(){

  }

}
