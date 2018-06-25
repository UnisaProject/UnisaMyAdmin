import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {BlockUI, NgBlockUI} from "ng-block-ui";

@Component({
  selector: 'unisa-material-student-input',
  templateUrl: './material-student-input.component.html',
  styleUrls: ['./material-student-input.component.scss']
})
export class MaterialStudentInputComponent implements OnInit {

  studentInputForm: FormGroup;

  /**
   * Reference to blockUI
   */
  @BlockUI()
  private blockUI: NgBlockUI;

  constructor(private router:Router,
              private formBuilder: FormBuilder) {
    this.initForm();
  }

  private initForm(){
    this.studentInputForm = this.formBuilder.group({
      studentNumber : [
        null,
        Validators.compose([
          Validators.required,
          Validators.maxLength(8),
          Validators.minLength(7),
          Validators.pattern("([0-9])*")
        ])
      ],
      studentSurname: [null,  Validators.required],
      studentNames: [null,  Validators.required],
      studentDOB: [null,  Validators.required],
    });
  }

  ngOnInit() {
  }

  onSubmit() {
    this.blockUI.start("Loading courses...");
    this.router.navigate(["courses"]);
  }

}
