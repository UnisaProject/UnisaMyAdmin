import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {CreditCardFormService} from "../../services/creditcard-form.service";
import {CreditCardPaymentForm} from "../../info-objects";
import {BlockUI, NgBlockUI} from "ng-block-ui";
import {CreditCardPaymentService} from "../../services/credit-card-payment.service";

@Component({
  selector: 'unisa-qual-input',
  templateUrl: './qual-input.component.html',
  styleUrls: ['./qual-input.component.scss']
})
export class QualInputComponent implements OnInit {

  creditCardPaymentForm: CreditCardPaymentForm;
  qualInputForm: FormGroup;

  /**
   * Reference to blockUI
   */
  @BlockUI()
  private blockUI: NgBlockUI;

  constructor(private router:Router,
              private formBuilder: FormBuilder,
              private creditCardFormService: CreditCardFormService,
              private creditCardPaymentService: CreditCardPaymentService) {

    this.qualInputForm = this.formBuilder.group({
      qualCode : [
        null,
        Validators.compose([
          Validators.required,
          Validators.maxLength(5),
          Validators.minLength(5),
          Validators.pattern("([0-9])*")
        ])
      ]
    });
  }

  ngOnInit(): void {
    this.blockUI.stop();
    this.creditCardPaymentForm = this.creditCardFormService.creditCardPaymentForm;
    if(this.creditCardPaymentForm === null || this.creditCardPaymentForm.studentInfo == null || this.creditCardPaymentForm.studentInfo.studentNumber === null){
      this.router.navigateByUrl("/studentInput")
    }
    else {
      this.qualInputForm.patchValue({
        qualCode : this.creditCardPaymentForm.qualificationInfo.qualCode
      });
    }
  }

  onSubmit(){
    this.blockUI.start("Loading account...");
    this.creditCardFormService.creditCardPaymentForm.qualificationInfo.qualCode = this.qualInputForm.value.qualCode;

    if(this.creditCardFormService.creditCardPaymentForm.regStatus === "TN"){
      this.creditCardPaymentService.getSmartCardValue(this.creditCardFormService.creditCardPaymentForm.studentInfo.studentNumber).subscribe((smartCardValue)=>{
        this.creditCardFormService.creditCardPaymentForm.canCancelSmartCard = (smartCardValue === "W");
        this.router.navigateByUrl('/tpPayment');
      }, (error) =>{
        this.blockUI.stop();
      });
    }
    else{
      this.router.navigateByUrl('/nonTpPayment');
    }
  }

  back(){
    this.creditCardFormService.creditCardPaymentForm = null;
    this.router.navigateByUrl('/studentInput');
  }

  cancel(){
    this.creditCardFormService.creditCardPaymentForm = null;
    this.router.navigateByUrl('/studentInput');
  }
}
