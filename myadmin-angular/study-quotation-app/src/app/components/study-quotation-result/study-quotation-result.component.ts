import {Component, OnInit} from '@angular/core';
import {BlockUI, NgBlockUI} from "ng-block-ui";
import {StudyFeeCriteriaService, StudyFeeQuotationService} from '../../services';
import {StudyQuotationRequest} from '../../info-objects';
import {StudyQuotation} from "../../info-objects/study-quotation";

@Component({
  selector: 'app-study-quotation-result',
  templateUrl: './study-quotation-result.component.html',
  styleUrls: ['./study-quotation-result.component.css']
})
export class StudyQuotationResultComponent implements OnInit {

  public studyQuotationInfo:StudyQuotation;

  @BlockUI()
  private blockUI: NgBlockUI;

  constructor(private studyFeeCriteriaService: StudyFeeCriteriaService,
              private studyFeeQuotationService: StudyFeeQuotationService) { }

  ngOnInit() {
    this.calculateStudyQuotation(this.studyFeeCriteriaService.searchCriteria);
  }

  private calculateStudyQuotation(searchCriteria: StudyQuotationRequest): void {
    this.studyFeeQuotationService.calculateStudyQuotation(searchCriteria)
      .subscribe((studyQuotationInfo: StudyQuotation) => {
          this.studyQuotationInfo = studyQuotationInfo;
        },
        error => {
          console.log(error);
          this.blockUI.stop();
        },
        () => {
          this.blockUI.stop();
        }
      );
  }

}
