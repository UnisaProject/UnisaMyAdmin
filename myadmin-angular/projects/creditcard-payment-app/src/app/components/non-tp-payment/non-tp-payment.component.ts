import {Component, OnInit} from "@angular/core";
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

  nonTpForm:FormGroup;
  //creditCardPaymentForm: CreditCardPaymentForm;
  nonTpPaymentInfo:NonTpPaymentInfo;


  /**
   * Reference to blockUI
   */
  @BlockUI()
  private blockUI:NgBlockUI;

  constructor(private router:Router,
              private formBuilder:FormBuilder,
              private creditCardFormService:CreditCardFormService,
              private creditCardPaymentService:CreditCardPaymentService) {
    this.initForm();
  }

  private initForm() {
    this.nonTpForm = this.formBuilder.group({
      email: [null, Validators.email],
      libraryFineFee: [0, Validators.required],
      payMatricFirstAppFee: [false, Validators.required],
      payLibraryFee: [false, Validators.required],
      payThreeGDataBundleFee: [false, Validators.required],
      studyFeeAmount: [0, Validators.required],
      ccTotalAmountInput: [0, Validators.required]
    });
  }

  ngOnInit() {
    this.blockUI.stop();
    if (this.creditCardFormService.creditCardPaymentForm === null || this.creditCardFormService.creditCardPaymentForm.studentInfo === null || this.creditCardFormService.creditCardPaymentForm.studentInfo.studentNumber === null) {
      this.router.navigateByUrl("/studentInput")
    } else {
      this.nonTpPaymentInfo = new NonTpPaymentInfo(this.creditCardFormService.creditCardPaymentForm);
      this.nonTpForm.patchValue({...this.nonTpPaymentInfo});
    }
  }

  back() {
    this.nonTpForm.reset();
    this.creditCardFormService.creditCardPaymentForm.creditCardInfo = null;
    this.router.navigateByUrl("/qualInput");
  }

  close() {
    this.nonTpForm.reset();
    this.creditCardFormService.creditCardPaymentForm = null;
    //TODO if student must return to sakai portal finance tool
    //TODO else unisa website
    this.router.navigateByUrl("/externalRedirect");
    //this.router.navigateByUrl("/studentInput");
  }

  cancel() {
    this.nonTpForm.reset();
    this.creditCardFormService.creditCardPaymentForm = null;
    //TODO if student must return to sakai portal finance tool
    //TODO else below
    this.router.navigateByUrl("/studentInput");
  }

  payNow() {
    this.blockUI.start("Processing transaction...");
    //Map Form Model to Data Model
    const formValue = this.nonTpForm.value;
    this.nonTpPaymentInfo.studentInfo.emailAddress = formValue.email;

    this.nonTpPaymentInfo.payLibraryFee = formValue.payLibraryFee;
    this.nonTpPaymentInfo.payMatricFirstAppFee = formValue.payMatricFirstAppFee;
    this.nonTpPaymentInfo.payThreeGDataBundleFee = formValue.payThreeGDataBundleFee;
    this.nonTpPaymentInfo.libraryFineFee = formValue.libraryFineFee;
    this.nonTpPaymentInfo.studyFeeAmount = formValue.studyFeeAmount;
    this.nonTpPaymentInfo.creditCardInfo = {...formValue.creditCardInfo};
    this.nonTpPaymentInfo.creditCardTotalAmountInput = formValue.ccTotalAmountInput;

    this.creditCardPaymentService.processNonTpPayment(this.nonTpPaymentInfo).subscribe((summaryInfo)=> {
      this.creditCardFormService.summaryInfo = {...summaryInfo};
      this.nonTpForm.reset();
      this.creditCardFormService.creditCardPaymentForm = null;
      this.router.navigateByUrl('/summary');
    }, (error)=> {
      this.blockUI.stop();
    });
  }
}
