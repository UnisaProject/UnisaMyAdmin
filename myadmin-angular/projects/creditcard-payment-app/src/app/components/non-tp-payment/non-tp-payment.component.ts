import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {CreditCardFormService} from "../../services/creditcard-form.service";
import {Router} from "@angular/router";
import {BlockUI, NgBlockUI} from "ng-block-ui";
import {NonTpPaymentInfo} from "../../info-objects/non-tp-payment-info";
import {CreditCardPaymentService} from "../../services/credit-card-payment.service";

@Component({
  selector: 'unisa-non-tp-payment',
  templateUrl: './non-tp-payment.component.html',
  styleUrls: ['./non-tp-payment.component.scss']
})
export class NonTpPaymentComponent implements OnInit {

  nonTpForm: FormGroup;
  //creditCardPaymentForm: CreditCardPaymentForm;
  nonTpPaymentInfo: NonTpPaymentInfo;


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
    this.nonTpForm = this.formBuilder.group({
      libraryFineFeeAmountInput : [0, Validators.required],
      payMatricFirstAppFee : [false, Validators.required],
      studyFeeAmountInput : [0, Validators.required],
      ccTotalAmountInput : [0, Validators.required]
    });
  }


  ngOnInit() {
    this.blockUI.stop();
    this.nonTpPaymentInfo = new NonTpPaymentInfo(this.creditCardFormService.creditCardPaymentForm);
    if(this.nonTpPaymentInfo === null ||  this.nonTpPaymentInfo.studentInfo === null || this.nonTpPaymentInfo.studentInfo.studentNumber === null){
      this.router.navigateByUrl("/studentInput")
    }
    else {
      this.nonTpForm.patchValue({...this.nonTpPaymentInfo});
    }
  }

  back(){
    this.nonTpForm.reset();
    this.creditCardFormService.creditCardPaymentForm.creditCardInfo = null;
    this.creditCardFormService.nonTpPaymentInfo.creditCardInfo = null;
    this.router.navigateByUrl("/qualInput");
  }

  close(){
    this.nonTpForm.reset();
    this.creditCardFormService.creditCardPaymentForm = null;
    this.creditCardFormService.nonTpPaymentInfo = null;
    this.router.navigateByUrl("/studentInput");
  }

  cancel(){
    this.nonTpForm.reset();
    this.creditCardFormService.creditCardPaymentForm = null;
    this.creditCardFormService.nonTpPaymentInfo = null;
    this.router.navigateByUrl("/studentInput");
  }

  payNow(){
    this.blockUI.start("Processing transaction...");
    const formValue = this.nonTpForm.value;
    //this.applicationPaymentInfo.studentInfo.emailAddress = formValue.email;
    this.nonTpPaymentInfo.creditCardInfo = formValue.creditCardInfo;

    // const applicationPaymentInfo: ApplicationPaymentInfo = {
    //   cardInfo : {...formValue.creditCardInfo},
    //   applyAmountInput : formValue.applyAmount,
    //   creditCardTotalAmountInput : formValue.ccTotalAmountInput,
    //   studentInfo : this.creditCardPaymentForm.studentInfo
    // };

    this.creditCardPaymentService.processNonTpPayment(this.nonTpPaymentInfo).subscribe((summaryInfo)=>{
      this.blockUI.stop();
    }, (error)=>{
      this.blockUI.stop();
    });
  }

}
