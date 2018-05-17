import {Component, OnInit} from '@angular/core';
import {BlockUI, NgBlockUI} from "ng-block-ui";
import {StudyFeeCriteriaService, StudyFeeQuotationService} from '../../services';
import {StudyQuotationRequestInfo, StudyQuotationInfo} from '../../info-objects';
import {HttpErrorResponse} from '@angular/common/http';
import {Router} from "@angular/router";

@Component({
  selector: 'app-study-quotation-result',
  templateUrl: './study-quotation-result.component.html',
  styleUrls: ['./study-quotation-result.component.scss']
})
export class StudyQuotationResultComponent implements OnInit {

  public studyQuotationInfo:StudyQuotationInfo;
  public errorMessage:string;

  @BlockUI()
  private blockUI:NgBlockUI;

  constructor(private studyFeeCriteriaService:StudyFeeCriteriaService,
              private studyFeeQuotationService:StudyFeeQuotationService,
              private router: Router) {
  }

  ngOnInit() {
    this.blockUI.start("Loading quote...");
    if(this.studyFeeCriteriaService.searchCriteria === null){
      this.router.navigateByUrl("search");
    }
    else{
      this.calculateStudyQuotation(this.studyFeeCriteriaService.searchCriteria);
    }
  }

  private calculateStudyQuotation(searchCriteria:StudyQuotationRequestInfo):void {
    this.studyFeeQuotationService.calculateStudyQuotation(searchCriteria)
      .subscribe((studyQuotationInfo:StudyQuotationInfo) => {
          this.studyQuotationInfo = studyQuotationInfo;
          if(this.studyQuotationInfo.coolgenErrorMsg){
            this.errorMessage = this.studyQuotationInfo.coolgenErrorMsg;
          }
          this.blockUI.stop();
        },
        response => {
          this.studyQuotationInfo = <StudyQuotationInfo> {
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
