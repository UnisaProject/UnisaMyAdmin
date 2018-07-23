import {Component, OnInit} from '@angular/core';
import {BlockUI, NgBlockUI} from "ng-block-ui";
import {RegistrationPeriodService, StudyFeeQuotationService} from '../../services';
import {StudyFeeQuotationRequestInfo, StudyFeeQuotationInfo} from '../../info-objects';
import {Router} from "@angular/router";

@Component({
  selector: 'unisa-study-quotation-result',
  templateUrl: './study-quotation-result.component.html',
  styleUrls: ['./study-quotation-result.component.scss']
})
export class StudyQuotationResultComponent implements OnInit {

  public studyQuotationInfo:StudyFeeQuotationInfo;
  public errorMessage:string;

  @BlockUI()
  private blockUI:NgBlockUI;

  constructor(private registrationPeriodService:RegistrationPeriodService,
              private studyFeeQuotationService:StudyFeeQuotationService,
              private router: Router) {
  }

  ngOnInit() {
    this.blockUI.start("Loading quote...");
    if(this.registrationPeriodService.searchCriteria === null){
      this.router.navigateByUrl("search");
    }
    else{
      // this.calculateStudyQuotation(this.registrationPeriodService.searchCriteria);
      this.requestStudyFeeQuotation(this.registrationPeriodService.searchCriteria);
    }
  }

  private calculateStudyQuotation(searchCriteria:StudyFeeQuotationRequestInfo):void {
    this.studyFeeQuotationService.calculateStudyQuotation(searchCriteria)
      .subscribe((studyQuotationInfo:StudyFeeQuotationInfo) => {
          this.studyQuotationInfo = studyQuotationInfo;
          if(this.studyQuotationInfo.message){
            this.errorMessage = this.studyQuotationInfo.message;
          }
          this.blockUI.stop();
        },
        response => {
          this.studyQuotationInfo = <StudyFeeQuotationInfo> {
            academicYear : searchCriteria.academicYear,
            qualification : searchCriteria.qualification,
            qualificationCode : searchCriteria.qualificationCode
          }
          if(response.error instanceof Error){
            this.errorMessage = response.error.message;
          }
          // If it looks like a framework error
          else if(response.error && response.error.message){
            this.errorMessage = response.error.message;
          }
          else {
            console.log(response);
            this.errorMessage = response.message;
          }
          this.blockUI.stop();
        });
  }

  private requestStudyFeeQuotation(searchCriteria:StudyFeeQuotationRequestInfo):void {
    this.studyFeeQuotationService.requestStudyFeeQuotation(searchCriteria)
      .subscribe((studyQuotationInfo:StudyFeeQuotationInfo) => {
          this.studyQuotationInfo = studyQuotationInfo;
          if(this.studyQuotationInfo.message){
            this.errorMessage = this.studyQuotationInfo.message;
          }
          this.blockUI.stop();
        },
        response => {
          this.studyQuotationInfo = <StudyFeeQuotationInfo> {
            academicYear : searchCriteria.academicYear,
            qualification : searchCriteria.qualification,
            qualificationCode : searchCriteria.qualificationCode
          }
          if(response.error instanceof Error){
            this.errorMessage = response.error.message;
          }
          // If it looks like a framework error
          else if(response.error && response.error.message){
            this.errorMessage = response.error.message;
          }
          else {
            console.log(response);
            this.errorMessage = response.message;
          }
          this.blockUI.stop();
        });
  }

}
