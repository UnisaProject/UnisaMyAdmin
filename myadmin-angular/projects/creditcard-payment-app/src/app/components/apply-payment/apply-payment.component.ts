import { Component, OnInit } from '@angular/core';
import {BlockUI, NgBlockUI} from "ng-block-ui";
import {FormBuilder, FormGroup, ValidationErrors, Validators} from "@angular/forms";
import {CreditCardPaymentService} from "../../services/credit-card-payment.service";
import {CreditCardFormService} from "../../services/creditcard-form.service";
import {Router} from "@angular/router";
import {ApplicationPaymentInfo} from "../../info-objects/application-payment-info";

@Component({
  selector: 'unisa-apply-payment',
  templateUrl: './apply-payment.component.html',
  styleUrls: ['./apply-payment.component.scss']
})
export class ApplyPaymentComponent implements OnInit {

  applyForm: FormGroup;

  applicationPaymentInfo: ApplicationPaymentInfo;

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
      email : [null, Validators.email],
      applyAmount : [0, Validators.required],
      ccTotalAmountInput : [0, Validators.required]
    });
  }



  ngOnInit() {
    this.blockUI.stop();
    this.applicationPaymentInfo = new ApplicationPaymentInfo(this.creditCardFormService.creditCardPaymentForm);

    if(this.applicationPaymentInfo === null ||  this.applicationPaymentInfo.studentInfo === null || this.applicationPaymentInfo.studentInfo.studentNumber === null){
      this.router.navigateByUrl("/studentInput")
    }
    else {
      this.applyForm.patchValue({
        email: this.applicationPaymentInfo.studentInfo.emailAddress
      });
    }
  }

  back(){
    this.applyForm.reset();
    this.creditCardFormService.applicationPaymentInfo.cardInfo = null;
    this.router.navigateByUrl("/studentInput")
  }

  close(){
    this.applyForm.reset();
    this.creditCardFormService.applicationPaymentInfo = null;
    this.router.navigateByUrl("/studentInput")
  }

  cancel(){
    this.applyForm.reset();
    this.creditCardFormService.applicationPaymentInfo = null;
    this.router.navigateByUrl("/studentInput")
  }

  payNow(){
    this.blockUI.start("Processing transaction...");
    const formValue = this.applyForm.value;
    this.applicationPaymentInfo.studentInfo.emailAddress = formValue.email;
    this.applicationPaymentInfo.cardInfo = formValue.creditCardInfo;

    // const applicationPaymentInfo: ApplicationPaymentInfo = {
    //   cardInfo : {...formValue.creditCardInfo},
    //   applyAmountInput : formValue.applyAmount,
    //   creditCardTotalAmountInput : formValue.ccTotalAmountInput,
    //   studentInfo : this.creditCardPaymentForm.studentInfo
    // };

    this.creditCardPaymentService.processApplicationPayment(this.applicationPaymentInfo).subscribe((summaryInfo)=>{
      this.blockUI.stop();
    }, (error)=>{
      this.blockUI.stop();
    });
  }

}
