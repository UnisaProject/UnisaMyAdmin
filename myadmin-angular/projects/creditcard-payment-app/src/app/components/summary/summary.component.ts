import {Component, OnInit} from "@angular/core";
import {FormBuilder, FormGroup} from "@angular/forms";
import {SummaryInfo} from "../../info-objects/summary-info";
import {BlockUI, NgBlockUI} from "ng-block-ui";
import {CreditCardFormService} from "../../services/creditcard-form.service";
import {Router} from "@angular/router";

@Component({
  selector: 'unisa-summary',
  templateUrl: './summary.component.html',
  styleUrls: ['./summary.component.scss']
})
export class SummaryComponent implements OnInit {
  summaryForm:FormGroup;
  summaryInfo:SummaryInfo;

  /**
   * Reference to blockUI
   */
  @BlockUI()
  private blockUI:NgBlockUI;

  constructor(private router:Router,
              private formBuilder:FormBuilder,
              private creditCardFormService:CreditCardFormService) {
    this.initForm();
  }

  private initForm() {
    this.summaryForm = this.formBuilder.group({
      message: [null,]
    });
  }

  ngOnInit() {
    this.blockUI.stop();
    if (this.creditCardFormService.summaryInfo === null || this.creditCardFormService.summaryInfo.summaryMessage === null) {
      this.router.navigateByUrl("/studentInput")
    }
    else {
      this.summaryInfo = {...this.creditCardFormService.summaryInfo};
      this.summaryForm.patchValue({...this.summaryInfo});
    }
  }

  close() {
    this.summaryForm.reset();
    this.creditCardFormService.creditCardPaymentForm = null;
    this.creditCardFormService.summaryInfo = null;
    //TODO if student must return to sakai portal finance tool
    //TODO else unisa website
    this.router.navigateByUrl("/externalRedirect");
    //this.router.navigateByUrl("/studentInput")
  }

  cancel() {
    this.summaryForm.reset();
    this.creditCardFormService.creditCardPaymentForm = null;
    this.creditCardFormService.summaryInfo = null;
    //TODO if student must return to sakai portal finance tool
    //TODO else below
    this.router.navigateByUrl("/studentInput")
  }
}
