import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {BlockUI, NgBlockUI} from "ng-block-ui";
import {FormArray, FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {StudyQuotationInfo, StudyUnitInfo} from '../../info-objects';
import { StudyFeeCriteriaService } from '../../services';

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
      academicYear: new FormControl(new Date().getFullYear()),
      countryCode: new FormControl(),
      qualificationType: new FormControl('02011'),
      qualificationCode: new FormControl(),
      libraryCard: new FormControl('N'),
      matricExemption: new FormControl('N'),
      courseCodes: this.formBuilder.array([
        new FormControl(),
        new FormControl(),
        new FormControl(),
        new FormControl(),
        new FormControl(),
        new FormControl(),
      ])
    });
  }

  ngOnInit() {
    this.blockUI.stop();
  }

  onSubmit() {
    this.blockUI.start("Loading quote...");
    this.studyFeeCriteriaService.searchCriteria = {...this.studyFeeForm.value};
    this.router.navigate(["result"]);
  }

  get courseCodes():FormArray {
    return <FormArray>this.studyFeeForm.controls['courseCodes'];
  }

}
