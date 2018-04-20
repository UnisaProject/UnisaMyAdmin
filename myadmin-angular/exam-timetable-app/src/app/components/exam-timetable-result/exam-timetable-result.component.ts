import { Component, OnInit } from '@angular/core';
import { SearchCriteriaService } from '../../services/search-criteria.service';
import { ExamPeriodInfo, ExamPeriodDateInfo } from '../../info-objects';
import { ExamPeriodDateService } from '../../services';
import { SearchCriteriaInfo } from '../../info-objects/shared/search-criteria-info';

@Component({
  selector: 'app-exam-timetable-result',
  templateUrl: './exam-timetable-result.component.html',
  styleUrls: ['./exam-timetable-result.component.css']
})
export class ExamTimetableResultComponent implements OnInit {

  public today: Date;
  public examYear: number;
  public examPeriod: ExamPeriodInfo;
  public examPeriodDates: ExamPeriodDateInfo[];

  constructor(private examPeriodDateService: ExamPeriodDateService, private searchCriteriaService: SearchCriteriaService) { }

  ngOnInit() {
    this.today = new Date();
    // this.examYear = this.searchCriteriaService.searchCriteria.year;
    this.examPeriod = this.searchCriteriaService.searchCriteria.examPeriod;
    this.getExamPeriodDates(this.searchCriteriaService.searchCriteria);
  }

  private getExamPeriodDates(searchCriteria: SearchCriteriaInfo): void {
    this.examPeriodDateService.getExamPeriodDatesByYearAndExamPeriodCodeAndCourseCodes(searchCriteria.examPeriod.examYear, searchCriteria.examPeriod.code, searchCriteria.courseCodes)
      .subscribe((examPeriodDates: ExamPeriodDateInfo[]) => {
        this.examPeriodDates = examPeriodDates;
      },
        error => {
          console.log(error)
        },
        () => {
          //Done 
        }
      );
  }

}
