import { Component, OnInit } from '@angular/core';
import {BlockUI, NgBlockUI} from "ng-block-ui";
import { StudyFeeCriteriaService, StudyFeeQuotationService } from '../../services';
import { StudyQuotationRequest, StudyUnitInfo } from '../../info-objects';
import {Router} from '@angular/router';

@Component({
  selector: 'app-study-quotation-result',
  templateUrl: './study-quotation-result.component.html',
  styleUrls: ['./study-quotation-result.component.css']
})
export class StudyQuotationResultComponent implements OnInit {

  public studyQuotationInfo:StudyQuotationRequest;

  @BlockUI()
  private blockUI: NgBlockUI;

  constructor(private studyFeeCriteriaService: StudyFeeCriteriaService,
              private studyFeeQuotationService: StudyFeeQuotationService) { }

  ngOnInit() {
    this.blockUI.stop();
    this.calculateStudyQuotation(this.studyFeeCriteriaService.searchCriteria);
  }

  private calculateStudyQuotation(searchCriteria: StudyQuotationRequest): void {
    this.studyFeeQuotationService.calculateStudyQuotation(searchCriteria)
      .subscribe((studyQuotationInfo: StudyQuotationRequest) => {
          this.studyQuotationInfo = studyQuotationInfo;
        },
        error => {
          console.log(error)
        },
        () => {
          this.blockUI.stop()
        }
      );
  }

}
