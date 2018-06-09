import { Component, OnInit } from '@angular/core';
import {ExamPeriodInfo, ExaminationInfo, SearchCriteriaInfo} from '../../info-objects';
import { ExaminationService, SearchCriteriaService } from '../../services';
import {BlockUI, NgBlockUI} from "ng-block-ui";
import {Router} from "@angular/router";

/**
 * A component to display the results of requesting for an exam timetable
 */
@Component({
  selector: 'unisa-exam-timetable-result',
  templateUrl: './exam-timetable-result.component.html',
  styleUrls: ['./exam-timetable-result.component.scss']
})
export class ExamTimetableResultComponent implements OnInit {

  /**
   * Date right now
   */
  public today: Date;

  /**
   * Exam period information
   */
  public examPeriod: ExamPeriodInfo;

  /**
   * Examination periods information
   */
  public examinations: ExaminationInfo[] = [];


  /**
   * Reference to blockUI
   */
  @BlockUI()
  private blockUI: NgBlockUI;

  /**
   * Creates a new instance of the <code>ExamTimetableResultComponent</code>
   * @param {ExaminationService} examinationService
   * @param {SearchCriteriaService} searchCriteriaService
   * @param {Router} router
   */
  constructor(private examinationService: ExaminationService,
              private searchCriteriaService: SearchCriteriaService,
              private router: Router) { }

  /**
   * Lifecycle hook that is called after data-bound properties of a directive are
   * initialized.
   */
  ngOnInit() {
    this.blockUI.start();
    // If there is no search criteria navigate back to the search screen
    if(this.searchCriteriaService.searchCriteria === null){
      this.router.navigate(["search"]);
      return;
    }

    this.today = new Date();
    this.examPeriod = this.searchCriteriaService.searchCriteria.examPeriod;
    this.getExaminations(this.searchCriteriaService.searchCriteria);
  }

  private getExaminations(searchCriteria: SearchCriteriaInfo): void {
    this.examinationService.getExaminationsByYearAndExamPeriodCodeAndCourseCodes(searchCriteria.examPeriod.examYear, searchCriteria.examPeriod.code, searchCriteria.courseCodes)
      .subscribe(
        (examinations: ExaminationInfo[]) => {
          this.examinations = examinations;
          this.blockUI.stop()
        },
        () => {
          this.blockUI.stop()
        },
      );
  }

}
