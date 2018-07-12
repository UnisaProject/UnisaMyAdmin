import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {BlockUI, NgBlockUI} from 'ng-block-ui';

@Component({
  selector: 'unisa-forgot-student-search',
  templateUrl: './forgot-student-search.component.html',
  styleUrls: ['./forgot-student-search.component.scss']
})
export class ForgotStudentSearchComponent implements OnInit {

  studentInputForm: FormGroup;

  /**
   * Reference to blockUI
   */
  @BlockUI()
  private blockUI: NgBlockUI;

  constructor(private formBuilder: FormBuilder) {
    this.initForm();
  }

  private initForm(){
    this.studentInputForm = this.formBuilder.group({
      surname: [null,  Validators.required],
      firstNames: [null,  Validators.required],
      dateOfBirth: [null,  Validators.required],
      identityNumber: [null],
      passportNumber: [null]
    });
  }

  ngOnInit() {
  }

}
