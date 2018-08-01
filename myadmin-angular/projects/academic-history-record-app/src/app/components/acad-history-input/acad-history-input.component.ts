import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AcademicRecordService} from "../../services/academic-record.service";
import {AcademicRecordModuleService} from "../../services/academic-record-module.service";
import {BlockUI, NgBlockUI} from "ng-block-ui";

@Component({
  selector: 'unisa-acad-history-input',
  templateUrl: './acad-history-input.component.html',
  styleUrls: ['./acad-history-input.component.scss']
})
export class AcadHistoryInputComponent implements OnInit {

  studentInputForm: FormGroup;

  /**
   * Reference to blockUI
   */
  @BlockUI()
  private blockUI: NgBlockUI;

  constructor(private router:Router,
              private formBuilder: FormBuilder,
              private academicRecordService: AcademicRecordService
             ) {
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
      ]
    });
  }

  ngOnInit() {
  }

  onSubmit(){
    this.blockUI.start("Loading information...");
    this.router.navigateByUrl('/academicRecord');
    // this.academicRecordService.getStudentAcademicQualificationResults(this.studentInputForm.value.studentNumber).subscribe((academicRecordInfo:any)=>{
    //     this.router.navigateByUrl('/academicRecord')
    // }, (error)=>{
    //   this.blockUI.stop();
    // });
  }

}


