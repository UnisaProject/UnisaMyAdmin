import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {BlockUI, NgBlockUI} from 'ng-block-ui';
import {Router} from "@angular/router";
import {StudentInfo, StudentService}from 'myadmin-lib';
import {AcademicQualResultInfo} from '../../info-objects';
import {AcademicRecordModuleService} from '../../services/academic-record-module.service';

@Component({
  selector: 'unisa-display-qualifications',
  templateUrl: './display-qualifications.component.html',
  styleUrls: ['./display-qualifications.component.scss']
})
export class DisplayQualificationsComponent implements OnInit {

  public today: Date;

  isStudent :boolean=false;

  public studentInfo: StudentInfo;

  public academicQualificationRecords: AcademicQualResultInfo[] = [];

  studentInputForm: FormGroup;

  @BlockUI()
  private blockUI: NgBlockUI;

  constructor(private router :Router,
              private formBuilder: FormBuilder,
              private academicRecordModuleService : AcademicRecordModuleService) {
    this.today = new Date();
    const academicQualificationRecord: AcademicQualResultInfo = {
      studentNumber: 12345,
      firstRegistration : this.today,
      status : "Qualification Completed",
      qualDescription : "PGCE(SPFET)",
      lastRegistrationYear : 2016,
      gradCeremonyDate:'',
      auditFlag:true,
      qualificationCode : "03980"};
    this.academicQualificationRecords.push(academicQualificationRecord);
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
    this.blockUI.stop();
  }

  viewModuleResults(studentQual: AcademicQualResultInfo) {
    this.router.navigateByUrl('/academicRecordModules');
   // this.router.navigate(['/viewCourse', studentQual.studentNumber, studentQual.qualificationCode]);
  }

  studentExists():boolean {
    return true; //this.studentInfo == null;
  }

}
