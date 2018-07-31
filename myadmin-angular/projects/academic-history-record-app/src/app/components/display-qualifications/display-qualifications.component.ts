import { Component, OnInit } from '@angular/core';
import {BlockUI, NgBlockUI} from 'ng-block-ui';
import {Router} from "@angular/router";
import {StudentInfo}from 'myadmin-lib';
import {AcademicQualResultInfo} from '../../info-objects';

@Component({
  selector: 'unisa-display-qualifications',
  templateUrl: './display-qualifications.component.html',
  styleUrls: ['./display-qualifications.component.scss']
})
export class DisplayQualificationsComponent implements OnInit {

  public today: Date;

  isStudent :boolean=true;

  public studentInfo: StudentInfo;

  public academicQualificationRecords: AcademicQualResultInfo[] = [];

  @BlockUI()
  private blockUI: NgBlockUI;

  constructor(private router :Router ) {
    this.today = new Date();
    const academicQualificationRecord: AcademicQualResultInfo = {
      studentNumber: 12345,
      firstRegistration : this.today,
      status : "Qualification Completed",
      qualDescription : "PGCE(SPFET)",
      lastRegistrationYear : 2016,
      qualificationCode : "03980"};
    this.academicQualificationRecords.push(academicQualificationRecord);
  }

  ngOnInit() {
    this.blockUI.stop();
  }

  viewModuleResults(studentQual: AcademicQualResultInfo) {
    this.router.navigateByUrl('/academicRecordModules');
   // this.router.navigate(['/viewCourse', studentQual.studentNumber, studentQual.qualificationCode]);
  }

  studentExists():boolean {
    return this.studentInfo ==null;
  }

}
