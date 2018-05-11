import {Component, OnInit} from '@angular/core';
import {BlockUI, NgBlockUI} from "ng-block-ui";
import {StudyFeeCriteriaService, StudyFeeQuotationService} from '../../services';
import {StudyQuotationRequest, StudyQuotation} from '../../info-objects';
import {HttpErrorResponse} from '@angular/common/http';

@Component({
  selector: 'app-study-quotation-result',
  templateUrl: './study-quotation-result.component.html',
  styleUrls: ['./study-quotation-result.component.css']
})
export class StudyQuotationResultComponent implements OnInit {

  public studyQuotationInfo:StudyQuotation;
  public errorMessage:string;

  @BlockUI()
  private blockUI:NgBlockUI;

  constructor(private studyFeeCriteriaService:StudyFeeCriteriaService,
              private studyFeeQuotationService:StudyFeeQuotationService) {
  }

  ngOnInit() {
    this.blockUI.start("Loading quote...");
    this.calculateStudyQuotation(this.studyFeeCriteriaService.searchCriteria);
  }

  private calculateStudyQuotation(searchCriteria:StudyQuotationRequest):void {
    this.studyFeeQuotationService.calculateStudyQuotation(searchCriteria)
      .subscribe((studyQuotationInfo:StudyQuotation) => {
          this.studyQuotationInfo = studyQuotationInfo;
          this.blockUI.stop();
        },
        error => {
          if (error instanceof HttpErrorResponse) {
            console.log(error);
            this.errorMessage = error.message;
          }
          this.blockUI.stop();
        });
  }

}
