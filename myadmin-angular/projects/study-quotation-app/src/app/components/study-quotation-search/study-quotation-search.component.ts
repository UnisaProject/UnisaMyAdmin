import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {BlockUI, NgBlockUI} from "ng-block-ui";
import {FormArray, FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {StudyFeeQuotationRequestInfo} from '../../info-objects';
import { RegistrationPeriodService } from '../../services';
import {atleastOneCourseCode} from './atleast-one.validator';

@Component({
  selector: 'unisa-study-quotation-search',
  templateUrl: './study-quotation-search.component.html',
  styleUrls: ['./study-quotation-search.component.scss']
})
export class StudyQuotationSearchComponent implements OnInit {

  public studyFeeForm:FormGroup;

  @BlockUI()
  private blockUI:NgBlockUI;

  constructor(private router:Router, private formBuilder:FormBuilder, private registrationPeriodService: RegistrationPeriodService) {
    this.initForm();
  }

  /**
   * Create the form
   */
  private initForm():void {
    this.blockUI.start();
    this.studyFeeForm = this.formBuilder.group({
      academicYear: [null, Validators.required],
      countryCode: [null, Validators.required],
      qualification: [null, Validators.required],
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
    this.defaultForm();
  }

  private defaultForm(): void{
    if(this.registrationPeriodService.searchCriteria === null){
      this.resetForm();
    }
    else{
      this.studyFeeForm.patchValue(this.registrationPeriodService.searchCriteria)
      this.blockUI.stop()
    }
  }

  resetForm(): void{
    this.studyFeeForm.patchValue({
      countryCode : "1015",
      qualification: "99999",
      qualificationCode: null,
      libraryCard: false,
      matricExemption: false,
      courseCodes : [
        null,null,null,null,null,null
        ,null,null,null,null,null,null
        ,null,null,null,null,null,null
      ]
    });
    this.registrationPeriodService.getQuotationYear().subscribe(
      (year) => this.studyFeeForm.patchValue({academicYear : year}),
      error => {() => this.blockUI.stop();
        this.router.navigate(["closed"]);
      },
      ()=> this.blockUI.stop()
    );
  }

  onSubmit() {
    this.blockUI.start("Loading quote...");
    const criteria: StudyFeeQuotationRequestInfo = {...this.studyFeeForm.value};

    // Filter out any blank course codes
    criteria.courseCodes = criteria.courseCodes.filter(c => c !== null && c !== "");

    this.registrationPeriodService.searchCriteria = criteria;
    this.router.navigate(["result"]);
  }

  get courseCodes():FormArray {
    return <FormArray>this.studyFeeForm.controls['courseCodes'];
  }

}
