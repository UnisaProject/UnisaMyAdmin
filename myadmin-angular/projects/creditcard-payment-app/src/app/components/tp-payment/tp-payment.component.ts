import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {BlockUI, NgBlockUI} from "ng-block-ui";
import {TpPaymentInfo} from "../../info-objects";
import {CreditCardFormService} from "../../services/creditcard-form.service";
import {Router} from "@angular/router";
import {CreditCardPaymentService} from "../../services/credit-card-payment.service";

@Component({
  selector: 'unisa-tp-payment',
  templateUrl: './tp-payment.component.html',
  styleUrls: ['./tp-payment.component.scss']
})
export class TpPaymentComponent implements OnInit {

  tpForm: FormGroup;
  //creditCardPaymentForm: CreditCardPaymentForm;
  tpPaymentInfo: TpPaymentInfo;


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
    this.tpPaymentInfo = new TpPaymentInfo(this.creditCardFormService.creditCardPaymentForm);
    if(this.tpPaymentInfo === null ||  this.tpPaymentInfo.studentInfo === null || this.tpPaymentInfo.studentInfo.studentNumber === null){
      this.router.navigateByUrl("/studentInput")
    }
    else {
      this.tpForm.patchValue({...this.tpPaymentInfo});
    }
  }

  back(){
    this.tpForm.reset();
    this.creditCardFormService.creditCardPaymentForm.creditCardInfo = null;
    this.creditCardFormService.tpPaymentInfo.creditCardInfo = null;
    this.router.navigateByUrl("/qualInput")
  }

  close(){
    this.tpForm.reset();
    this.creditCardFormService.creditCardPaymentForm = null;
    this.creditCardFormService.tpPaymentInfo = null;
    this.router.navigateByUrl("/studentInput")
  }

  cancel(){
    this.tpForm.reset();
    this.creditCardFormService.creditCardPaymentForm = null;
    this.creditCardFormService.tpPaymentInfo = null;
    this.router.navigateByUrl("/studentInput")
  }

  payNow(){
    this.blockUI.start("Processing transaction...");
    const formValue = this.tpForm.value;
    //this.applicationPaymentInfo.studentInfo.emailAddress = formValue.email;
    this.tpPaymentInfo.creditCardInfo = formValue.creditCardInfo;

    // const applicationPaymentInfo: ApplicationPaymentInfo = {
    //   cardInfo : {...formValue.creditCardInfo},
    //   applyAmountInput : formValue.applyAmount,
    //   creditCardTotalAmountInput : formValue.ccTotalAmountInput,
    //   studentInfo : this.creditCardPaymentForm.studentInfo
    // };

    this.creditCardPaymentService.processTpPayment(this.tpPaymentInfo).subscribe((summaryInfo)=>{
      this.blockUI.stop();
    }, (error)=>{
      this.blockUI.stop();
    });
  }

}
