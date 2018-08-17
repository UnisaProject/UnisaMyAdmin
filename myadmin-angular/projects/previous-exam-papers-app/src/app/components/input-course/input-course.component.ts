import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
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
      lmsCourseCode : [null, Validators.required]
    });
  }

  ngOnInit() {
  }

  onSubmit() {
    const lmsCourseCode = this.inputForm.value.lmsCourseCode;
    this.blockUI.start("Loading exam papers...");
    this.router.navigate(['examPapers', lmsCourseCode]);
  }
}
