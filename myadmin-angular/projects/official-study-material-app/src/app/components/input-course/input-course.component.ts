import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {StudentModuleEnrolmentInfo} from "../../../../../temp-study-material-app/src/app/info-objects";
import {BlockUI, NgBlockUI} from "ng-block-ui";
import {Router} from "@angular/router";

@Component({
  selector: 'unisa-input-course',
  templateUrl: './input-course.component.html',
  styleUrls: ['./input-course.component.scss']
})
export class InputCourseComponent implements OnInit {

  inputForm: FormGroup;

  /**
   * Reference to blockUI
   */
  @BlockUI()
  private blockUI: NgBlockUI;

  constructor(private formBuilder: FormBuilder,
              private router: Router) {
    this.initForm();
  }


  private initForm(): void{
    this.inputForm = this.formBuilder.group({
      lmsCourseId : [null, Validators.required]
    });
  }

  ngOnInit() {
  }

  onSubmit() {
    const lmsCourseId = this.inputForm.value.lmsCourseId;
    this.blockUI.start("Loading study material...");
    this.router.navigate(['studyMaterial', lmsCourseId]);
  }
}
