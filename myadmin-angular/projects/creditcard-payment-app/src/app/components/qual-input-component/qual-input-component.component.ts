import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {CreditCardFormService} from "../../services/creditcard-form.service";
import {CreditCardPaymentForm} from "../../info-objects";

@Component({
  selector: 'unisa-qual-input-component',
  templateUrl: './qual-input-component.component.html',
  styleUrls: ['./qual-input-component.component.scss']
})
export class QualInputComponentComponent implements OnInit {

  creditCardPaymentForm: CreditCardPaymentForm;
  qualInputForm: FormGroup;

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
    this.creditCardPaymentForm = this.creditCardFormService.creditCardPaymentForm;
    if(this.creditCardPaymentForm === null ||  this.creditCardPaymentForm.studentNumber === null){
      this.router.navigateByUrl("/studentInput")
    }
    else {
      // TODO fake some data for now
      this.creditCardPaymentForm.qualDesc = "PGCE (INT & SENIOR PHASE)";
      this.creditCardPaymentForm.qualCode = "02623";
      this.qualInputForm.patchValue({...this.creditCardPaymentForm});
    }
  }

  onSubmit(){
    this.router.navigateByUrl('nonTpPayment')
  }

  back(){

  }

  cancel(){

  }
}
