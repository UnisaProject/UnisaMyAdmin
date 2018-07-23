import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { ExamPeriodService } from '../../services';
import { ExamPeriodInfo } from '../../info-objects';
import { SearchCriteriaService } from '../../services';
import {FormArray, FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {atleastOneCourseCode} from "./atleast-one.validator";
import {selectedExamPeriod} from "./exam-period.validator";
import {BlockUI, NgBlockUI} from "ng-block-ui";
import {DescriptionPipe, OrderByPipe} from "myadmin-lib";
import {Observable} from "rxjs/Rx";
import {tap} from "rxjs/operators";

/**
 * Component to allow a user to enter criteria to search for an exam timetable.
 */
@Component({
  selector: 'unisa-exam-timetable-search',
  templateUrl: './exam-timetable-search.component.html',
  styleUrls: ['./exam-timetable-search.component.scss'],
  providers : [
    OrderByPipe,
    DescriptionPipe,
  ]
})
export class ExamTimetableSearchComponent implements OnInit {

  /**
   * Current date to display on the page
   */
  public today: Date;

  /**
   * Exam periods the user can choose from
   */
  public examPeriods: ExamPeriodInfo[] = [];

  /**
   * The form the user is busy completing on screen
   */
  public searchForm: FormGroup;

  /**
   * Reference to blockUI
   */
  @BlockUI()
  private blockUI: NgBlockUI;


  /**
   * Creates a new instance of this ExamTimetableSearchComponent
   * @param {Router} router
   * @param {ExamPeriodService} examPeriodService
   * @param {SearchCriteriaService} searchCriteriaService
   * @param {FormBuilder} formBuilder
   * @param orderByPipe
   */
  constructor(private router: Router,
              private examPeriodService: ExamPeriodService,
              private searchCriteriaService: SearchCriteriaService,
              private formBuilder: FormBuilder,
              private orderByPipe: OrderByPipe) {
    this.initForm();
  }

  /**
   * Create the form
   */
  private initForm(): void {
    this.blockUI.start();
    this.searchForm = this.formBuilder.group({
      examPeriod : [null, selectedExamPeriod()],
      courseCodes : this.formBuilder.array([
        new FormControl(),
        new FormControl(),
        new FormControl(),
        new FormControl(),
        new FormControl(),
        new FormControl(),
        new FormControl(),
        new FormControl(),
        new FormControl(),
        new FormControl(),
      ])
    }, { validator : Validators.compose([atleastOneCourseCode()])});
  }

  ngOnInit() {
    this.today = new Date();
    this.getExamPeriods().subscribe(
      ()=>{
        this.blockUI.stop();
        this.defaultForm()
      },
      (error)=>{
        this.blockUI.stop();
      });
  }

  /**
   * Set the form to a default state
   */
  private defaultForm(): void{
    if(this.searchCriteriaService.searchCriteria !== null){
      this.searchForm.patchValue(this.searchCriteriaService.searchCriteria);
    }
    else {
      this.resetForm();
    }
  }

  /**
   * Rest the form to a clean state
   */
  resetForm() : void{
    this.searchForm.patchValue({
      examPeriod : this.examPeriods[0],
      courseCodes : [null, null, null, null, null, null ]
    })
  }

  /**
   * Callback for when the form is submitted
   */
  onSubmit() {
    this.blockUI.start("Loading timetable...");
    this.searchCriteriaService.searchCriteria = {...this.searchForm.value};
    this.router.navigate(["result"]);
  }

  get courseCodes(): FormArray{
    return <FormArray>this.searchForm.controls['courseCodes'];
  }

  compareExamPeriod(periodA:ExamPeriodInfo , periodB:ExamPeriodInfo ) : boolean {
    if(periodB == null || !periodB.hasOwnProperty('examYear') ||!periodB.hasOwnProperty('code') ){
      return false;
    }
    return periodA.examYear === periodB.examYear && periodA.code === periodB.code;
  }

  /**
   * Call to get the exam periods
   * @returns {Promise<any>}
   */
  private getExamPeriods(): Observable<ExamPeriodInfo[]> {
    return this.examPeriodService.getExamPeriods()
      .pipe(
        tap((examPeriods: ExamPeriodInfo[]) => {
          this.examPeriods = this.orderByPipe.transform(examPeriods, 'code');
        })
      );
  }

}
