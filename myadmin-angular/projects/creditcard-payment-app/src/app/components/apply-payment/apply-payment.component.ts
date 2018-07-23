import {Component, OnInit, Inject} from "@angular/core";
import {BlockUI, NgBlockUI} from "ng-block-ui";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {CreditCardPaymentService} from "../../services/credit-card-payment.service";
import {CreditCardFormService} from "../../services/creditcard-form.service";
import {Router} from "@angular/router";
import {ApplicationPaymentInfo} from "../../info-objects/application-payment-info";
import {DOCUMENT} from "@angular/common";

@Component({
  selector: 'unisa-apply-payment',
  templateUrl: './apply-payment.component.html',
  styleUrls: ['./apply-payment.component.scss']
})
export class ApplyPaymentComponent implements OnInit {

  applyForm:FormGroup;

  applicationPaymentInfo:ApplicationPaymentInfo;

  /**
   * Reference to blockUI
   */
  @BlockUI()
  private blockUI:NgBlockUI;

  constructor(private router:Router,
              private formBuilder:FormBuilder,
              private creditCardFormService:CreditCardFormService,
              private creditCardPaymentService:CreditCardPaymentService,
              @Inject(DOCUMENT) private document:any) {
    this.initForm();
  }

  private initForm() {
    this.applyForm = this.formBuilder.group({
      email: [null, Validators.email],
      applyAmount: [0, Validators.required],
      ccTotalAmountInput: [0, Validators.required]
    });
  }


  ngOnInit() {
    this.blockUI.stop();
    if (this.creditCardFormService.creditCardPaymentForm === null || this.creditCardFormService.creditCardPaymentForm.studentInfo === null || this.creditCardFormService.creditCardPaymentForm.studentInfo.studentNumber === null) {
      this.router.navigateByUrl("/studentInput")
    } else {
      this.applicationPaymentInfo = new ApplicationPaymentInfo(this.creditCardFormService.creditCardPaymentForm);
      this.applyForm.patchValue({
        email: this.applicationPaymentInfo.studentInfo.emailAddress,
        applyAmount: this.applicationPaymentInfo.applyAmountInput,
        ccTotalAmountInput: this.applicationPaymentInfo.creditCardTotalAmountInput
      });
    }
  }

  back() {
    this.applyForm.reset();
    this.router.navigateByUrl("/studentInput")
  }

  close() {
    this.applyForm.reset();
    //TODO if student must return to sakai portal finance tool
    //TODO else unisa website
    this.router.navigateByUrl("/externalRedirect");
    //this.router.navigateByUrl("/studentInput")
  }

  cancel() {
    this.applyForm.reset();
    //TODO if student must return to sakai portal finance tool
    //TODO else below
    this.router.navigateByUrl("/studentInput")
  }

  payNow() {
    this.blockUI.start("Processing transaction...");
    const formValue = this.applyForm.value;
    this.applicationPaymentInfo.studentInfo.emailAddress = formValue.email;
    this.applicationPaymentInfo.cardInfo = {...formValue.creditCardInfo};
    this.applicationPaymentInfo.creditCardTotalAmountInput = formValue.ccTotalAmountInput;

    this.creditCardPaymentService.processApplicationPayment(this.applicationPaymentInfo).subscribe((summaryInfo)=> {
      this.creditCardFormService.summaryInfo = {...summaryInfo};
      this.applyForm.reset();
      this.creditCardFormService.creditCardPaymentForm = null;
      this.router.navigateByUrl('/summary');
    }, (error)=> {
      this.blockUI.stop();
    });
  }

}
