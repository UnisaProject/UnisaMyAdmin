import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
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
      this.nonTpForm.patchValue({...this.creditCardPaymentForm});
    }
  }

}
