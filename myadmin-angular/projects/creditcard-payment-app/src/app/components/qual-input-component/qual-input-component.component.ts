import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {CreditCardFormService} from "../../services/creditcard-form.service";
import {CreditCardPaymentForm} from "../../info-objects";
import {BlockUI, NgBlockUI} from "ng-block-ui";

@Component({
  selector: 'unisa-qual-input-component',
  templateUrl: './qual-input-component.component.html',
  styleUrls: ['./qual-input-component.component.scss']
})
export class QualInputComponentComponent implements OnInit {

  creditCardPaymentForm: CreditCardPaymentForm;
  qualInputForm: FormGroup;

  /**
   * Reference to blockUI
   */
  @BlockUI()
  private blockUI: NgBlockUI;

  constructor(private router:Router,
              private formBuilder: FormBuilder,
              private creditCardFormService: CreditCardFormService) {

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
    this.creditCardPaymentForm.qualificationInfo.qualCode = this.qualInputForm.value.qualCode;
    this.router.navigateByUrl('/nonTpPayment');
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
