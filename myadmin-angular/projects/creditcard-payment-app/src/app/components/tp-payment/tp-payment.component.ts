import {Component, OnInit} from "@angular/core";
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

  tpForm:FormGroup;
  //creditCardPaymentForm: CreditCardPaymentForm;
  tpPaymentInfo:TpPaymentInfo;


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
    this.tpForm = this.formBuilder.group({
      email: [null, Validators.email],
      libraryFineBalance: [0, Validators.required],
      dueImmediately: [0, Validators.required],
      minimumForReg: [0, Validators.required],
      fullAccount: [0, Validators.required],

      libraryFineFeeForStudent: [0, Validators.required],
      studyFeeAmount: [0, Validators.required],
      libraryFeeForStudent: [0, Validators.required],
      matricFeeForStudent: [0, Validators.required],
      ccTotalAmountInput: [0, Validators.required]
    });
  }


  ngOnInit() {
    this.blockUI.stop();
    if (this.creditCardFormService.creditCardPaymentForm === null || this.creditCardFormService.creditCardPaymentForm.studentInfo === null || this.creditCardFormService.creditCardPaymentForm.studentInfo.studentNumber === null) {
      this.router.navigateByUrl("/studentInput")
    } else {
      this.tpPaymentInfo = new TpPaymentInfo(this.creditCardFormService.creditCardPaymentForm);
      this.tpForm.patchValue({...this.tpPaymentInfo});
    }
  }

  back() {
    this.tpForm.reset();
    this.creditCardFormService.creditCardPaymentForm.creditCardInfo = null;
    this.router.navigateByUrl("/qualInput")
  }

  close() {
    this.tpForm.reset();
    this.creditCardFormService.creditCardPaymentForm = null;
    //TODO if student must return to sakai portal finance tool
    //TODO else unisa website
    this.router.navigateByUrl("/externalRedirect");
    //this.router.navigateByUrl("/studentInput")
  }

  cancel() {
    this.tpForm.reset();
    this.creditCardFormService.creditCardPaymentForm = null;
    //TODO if student must return to sakai portal finance tool
    //TODO else below
    this.router.navigateByUrl("/studentInput")
  }

  payNow() {
    this.blockUI.start("Processing transaction...");
    const formValue = this.tpForm.value;
    //Map Form Model to Data Model
    this.tpPaymentInfo.studentInfo.emailAddress = formValue.email;
    this.tpPaymentInfo.dueImmediately = formValue.dueImmediately;
    this.tpPaymentInfo.minimumForReg = formValue.minimumForReg;
    this.tpPaymentInfo.fullAccount = formValue.fullAccount;

    this.tpPaymentInfo.libraryFineFeeForStudent = formValue.libraryFineFeeForStudent;
    this.tpPaymentInfo.studyFeeAmount = formValue.studyFeeAmount;
    this.tpPaymentInfo.libraryFeeForStudent = formValue.libraryFeeForStudent;
    this.tpPaymentInfo.matricFeeForStudent = formValue.matricFeeForStudent;

    this.tpPaymentInfo.creditCardInfo = {...formValue.creditCardInfo};
    this.tpPaymentInfo.creditCardTotalAmountInput = formValue.ccTotalAmountInput;

    this.creditCardPaymentService.processTpPayment(this.tpPaymentInfo).subscribe((summaryInfo)=> {
      this.creditCardFormService.summaryInfo = {...summaryInfo};
      this.tpForm.reset();
      this.creditCardFormService.creditCardPaymentForm = null;
      this.router.navigateByUrl('/summary');
    }, (error)=> {
      this.blockUI.stop();
    });
  }

}
