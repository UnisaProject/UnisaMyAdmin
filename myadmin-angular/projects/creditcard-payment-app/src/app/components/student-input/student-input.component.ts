import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {CreditCardFormService} from "../../services/creditcard-form.service";
import {CreditCardPaymentService} from "../../services/credit-card-payment.service";
import {CreditCardPaymentInfo} from "../../info-objects";
import {BlockUI, NgBlockUI} from "ng-block-ui";

@Component({
  selector: 'unisa-student-component',
  templateUrl: './student-input.component.html',
  styleUrls: ['./student-input.component.scss']
})
export class StudentInputComponent implements OnInit {

  studentInputForm: FormGroup;


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
    this.studentInputForm = this.formBuilder.group({
      studentNumber : [
        null,
        Validators.compose([
          Validators.required,
          Validators.maxLength(8),
          Validators.minLength(7),
          Validators.pattern("([0-9])*")
        ])
      ]
    });
  }



  ngOnInit() {
  }

  onSubmit(){
    this.blockUI.start("Loading information...");
    this.creditCardPaymentService.processStudentInput(this.studentInputForm.value.studentNumber).subscribe((creditCardPaymentInfo:CreditCardPaymentInfo)=>{
      // Copy the data to the service
      this.creditCardFormService.creditCardPaymentForm = {...creditCardPaymentInfo};
      this.creditCardFormService.creditCardPaymentForm.canCancelSmartCard = false; // Default value

      if(creditCardPaymentInfo.regStatus === "AP"){
        this.router.navigateByUrl('/applyPayment')
      }
      else{
        this.router.navigateByUrl('/qualInput')
      }
    }, (error)=>{
      this.blockUI.stop();
    });


  }

}
