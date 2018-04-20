import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { ExamAdmissionService, ExamPeriodService } from '../../services';
import { ExamPeriodInfo } from '../../info-objects';
import { SearchCriteriaService } from '../../services';
import { FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";

/**
 * Component to allow a user to enter criteria to search for an exam timetable.
 */
@Component({
  selector: 'app-exam-timetable-search',
  templateUrl: './exam-timetable-search.component.html',
  styleUrls: ['./exam-timetable-search.component.css']
})
export class ExamTimetableSearchComponent implements OnInit {

  /**
   * Current date to display on the page
   */
  public today: Date;

  // TODO remove
  public examYears: number[];

  /**
   * Exam periods the user can choose from
   */
  public examPeriods: ExamPeriodInfo[];

  /**
   * The form the user is busy completing on screen
   */
  public searchForm: FormGroup;

  /**
   * Creates a new instance of this ExamTimetableSearchComponent
   * @param {Router} router
   * @param {ExamAdmissionService} examAdmissionService
   * @param {ExamPeriodService} examPeriodService
   * @param {SearchCriteriaService} searchCriteriaService
   * @param {FormBuilder} formBuilder
   */
  constructor(private router: Router,
              private examAdmissionService: ExamAdmissionService,
              private examPeriodService: ExamPeriodService,
              private searchCriteriaService: SearchCriteriaService,
              private formBuilder: FormBuilder) {
    this.initForm();
  }

  /**
   * Create the form
   */
  private initForm(): void {
    this.searchForm = this.formBuilder.group({
      examPeriod : [null, Validators.required],
      courseCodes : this.formBuilder.array([
        new FormControl(),
        new FormControl(),
        new FormControl(),
        new FormControl(),
        new FormControl(),
      ])
    })
  }

  ngOnInit() {
    this.today = new Date();
    this.getExamPeriods().then(()=>{
      this.searchForm.patchValue(this.searchCriteriaService.searchCriteria);
    });
  }

  /**
   * Callback for when the form is submitted
   */
  onSubmit() {
    this.searchCriteriaService.searchCriteria = {...this.searchForm.value};
    this.router.navigate(["result"]);
  }

  compareExamPeriod(periodA:ExamPeriodInfo , periodB:ExamPeriodInfo ) : boolean {
    return periodA.examYear === periodB.examYear && periodA.code === periodB.code;
  }

  /**
   * Call to get the exam periods
   * @returns {Promise<any>}
   */
  private getExamPeriods(): Promise<any> {
    return this.examPeriodService.getExamPeriods().toPromise()
      .then((examPeriods: ExamPeriodInfo[]) => {
        this.examPeriods = examPeriods;
      },error => {
          console.log(error)
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
