import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { ExamAdmissionService, ExamPeriodService } from './../../services';
import { ExamAdmissionInfo, ExamPeriodInfo } from './../../info-objects';
import { SearchCriteriaService } from './../../services/search-criteria.service';
import { SearchCriteriaInfo } from '../../info-objects/shared/search-criteria-info';
import { DescriptionInfo } from '../../info-objects/shared';

@Component({
  selector: 'app-exam-timetable-search',
  templateUrl: './exam-timetable-search.component.html',
  styleUrls: ['./exam-timetable-search.component.css']
})
export class ExamTimetableSearchComponent implements OnInit {

  public today: Date;
  public examYears: number[];
  public examPeriods: ExamPeriodInfo[];
  public searchCriteria: SearchCriteriaInfo;

  constructor(private router: Router,
    private examAdmissionService: ExamAdmissionService,
    private examPeriodService: ExamPeriodService,
    private searchCriteriaService: SearchCriteriaService) { }

  ngOnInit() {
    this.today = new Date();
    this.searchCriteria = this.searchCriteriaService.searchCriteria;
    this.getExamPeriods();
  }

  onSubmit() {
    this.searchCriteriaService.searchCriteria = this.searchCriteria;
    this.router.navigate(["result"]);
  }

  private getExamPeriods(): void {
    this.examPeriodService.getExamPeriods()
      .subscribe((examPeriods: ExamPeriodInfo[]) => {
        this.examPeriods = examPeriods;
      },
        error => {
          console.log(error)
        },
        () => {
          //Done 
        }
      );

    /*this.examAdmissionService.getExamAdmissions()
      .mergeMap((examAdmissions: ExamAdmissionInfo[]) => {
        this.examYears = this.unique(examAdmissions.map(examAdmission => examAdmission.year));
        let examPeriodCodes = this.unique(examAdmissions.map(examAdmission => examAdmission.examPeriodCode));
        return this.examPeriodService.getExamPeriodByCodes(examPeriodCodes);
      })
      .subscribe((examPeriods: ExamPeriodInfo[]) => {
        this.examPeriods = examPeriods;
      },
        error => {
          console.log(error)
        },
        () => {
          //Done 
        }
      );*/
  }

  /*private unique(arr) {
    return arr.sort().filter(function (item, pos, ary) {
      return !pos || item != ary[pos - 1];
    })
  }*/

}
