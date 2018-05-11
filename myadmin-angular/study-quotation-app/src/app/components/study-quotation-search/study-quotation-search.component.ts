import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {BlockUI, NgBlockUI} from "ng-block-ui";
import {FormArray, FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {StudyQuotationRequest, StudyUnitInfo} from '../../info-objects';
import { StudyFeeCriteriaService } from '../../services';
import {atleastOneCourseCode} from './atleast-one.validator';

@Component({
  selector: 'app-study-quotation-search',
  templateUrl: './study-quotation-search.component.html',
  styleUrls: ['./study-quotation-search.component.css']
})
export class StudyQuotationSearchComponent implements OnInit {

  public studyFeeForm:FormGroup;

  @BlockUI()
  private blockUI:NgBlockUI;

  constructor(private router:Router, private formBuilder:FormBuilder, private studyFeeCriteriaService: StudyFeeCriteriaService) {
    this.initForm();
  }

  /**
   * Create the form
   */
  private initForm():void {
    this.blockUI.start();
    this.studyFeeForm = this.formBuilder.group({
      academicYear: [null, Validators.required],
      countryCode: ["1015", Validators.required],
      qualificationType: ["02011", Validators.required],
      qualificationCode: [null, Validators.required],
      libraryCard: [false, Validators.required],
      matricExemption: [false, Validators.required],
      courseCodes: this.formBuilder.array([
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
        new FormControl(),
        new FormControl(),
        new FormControl(),
        new FormControl(),
        new FormControl(),
        new FormControl(),
        new FormControl(),
        new FormControl(),
      ])
    },{ validator : Validators.compose([atleastOneCourseCode()])});
  }

  ngOnInit() {
    // TODO only set this the criteria was not empty

    this.studyFeeCriteriaService.getQuotationYear().subscribe(
      (year) => this.studyFeeForm.patchValue({academicYear : year}),
      () => this.blockUI.stop(),
      ()=> this.blockUI.stop()
    );

  }

  onSubmit() {
    this.blockUI.start("Loading quote...");
    const criteria: StudyQuotationRequest = {...this.studyFeeForm.value};

    // Filter out any blank course codes
    criteria.courseCodes = criteria.courseCodes.filter(c => c !== null && c !== "");

    this.studyFeeCriteriaService.searchCriteria = criteria;
    this.router.navigate(["result"]);
  }

  get courseCodes():FormArray {
    return <FormArray>this.studyFeeForm.controls['courseCodes'];
  }

}
