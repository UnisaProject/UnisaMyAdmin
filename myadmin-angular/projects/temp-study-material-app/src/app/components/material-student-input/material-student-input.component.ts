import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {BlockUI, NgBlockUI} from 'ng-block-ui';
import {StudyMaterialService, StudentModuleEnrolmentInfo} from 'myadmin-lib';
import {StudyMaterialFormService} from "../../services";

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
              private studyMaterialFormService:StudyMaterialFormService,
              private studyMaterialService: StudyMaterialService,
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
      surname: [null,  Validators.required],
      firstNames: [null,  Validators.required],
      dateOfBirth: [null,  Validators.required],
    });
  }

  ngOnInit() {
  }

  onSubmit() {
    this.studyMaterialFormService.studentInfo = {...this.studentInputForm.value};
    this.blockUI.start("Loading courses...");
    this.studyMaterialService.requestStudentModuleEnrolments(this.studyMaterialFormService.studentInfo).subscribe((moduleEnrolments:StudentModuleEnrolmentInfo[])=>{
      // Copy the data to the service
     this.studyMaterialFormService.studentModuleEnrolmentList = [...moduleEnrolments];
     this.router.navigateByUrl('/courses');
    }, (error)=>{
      this.blockUI.stop();
    });
  }

}
