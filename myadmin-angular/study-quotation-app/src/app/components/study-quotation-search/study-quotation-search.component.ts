import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {BlockUI, NgBlockUI} from "ng-block-ui";
import {FormArray, FormBuilder, FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-study-quotation-search',
  templateUrl: './study-quotation-search.component.html',
  styleUrls: ['./study-quotation-search.component.css']
})
export class StudyQuotationSearchComponent implements OnInit {

  public today: Date;

  public studyFeeForm: FormGroup;

  @BlockUI()
  private blockUI: NgBlockUI;

  constructor(private router: Router, private formBuilder: FormBuilder) {
    this.initForm();
  }

  /**
   * Create the form
   */
  private initForm(): void {
    this.blockUI.start();
    //Todo need 3x6 coursecode array
    this.studyFeeForm = this.formBuilder.group({
      courseCodes : this.formBuilder.array([
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
    this.today = new Date();
    this.blockUI.stop();
  }

  onSubmit() {
    this.blockUI.start("Loading quote...");
    this.router.navigate(["result"]);
  }

  get courseCodes(): FormArray{
    return <FormArray>this.studyFeeForm.controls['courseCodes'];
  }

}
