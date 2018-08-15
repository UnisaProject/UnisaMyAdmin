import { Component, OnInit } from '@angular/core';
import {BlockUI, NgBlockUI} from 'ng-block-ui';
import {Router} from "@angular/router";
import {ErrorInfo, StudentInfo, ToasterNotificationService}from 'myadmin-lib';
import {AcademicModuleRecordInfo,StudentAcademicQualificationRecordInfo} from '../../info-objects';
import {AcademicRecordService} from '../../services/academic-record.service';
import {AcademicRecordModuleService} from '../../services/academic-record-module.service';
import {SearchCriteriaService} from '../../services/search-criteria.service';

@Component({
  selector: 'unisa-display-qual-modules',
  templateUrl: './display-qual-modules.component.html',
  styleUrls: ['./display-qual-modules.component.scss']
})
export class DisplayQualModulesComponent implements OnInit {

  creditsOnly:string='N';

  isStudent :boolean=false;

  public studentInfo: StudentInfo;

  public academicModuleRecords: AcademicModuleRecordInfo[] = [];

  public selectedAcademicQualification : StudentAcademicQualificationRecordInfo;

  @BlockUI()
  private blockUI: NgBlockUI;

  constructor(private router :Router,
              private academicRecordModuleService : AcademicRecordModuleService,
              private academicRecordService : AcademicRecordService,
              private searchCriteriaService : SearchCriteriaService,
              private toaster : ToasterNotificationService) {
  }

  ngOnInit() {
    this.blockUI.stop();
    if(this.searchCriteriaService.studentInfo === null || this.searchCriteriaService.selectedQualification === null){
      this.router.navigate(["academicRecord"]);
      return;
    }
    this.studentInfo = {...this.searchCriteriaService.studentInfo};
    this.selectedAcademicQualification = {...this.searchCriteriaService.selectedQualification};
    this.getAcademicRecordModuleList();
  }

  private getAcademicRecordModuleList():void {
    this.blockUI.start("Loading modules...");
    this.academicRecordModuleService.getStudentAcademicModuleResults(this.studentInfo.studentNumber, this.selectedAcademicQualification.qualificationCode, false)
      .subscribe(
        (modules:AcademicModuleRecordInfo[]) => {
          this.academicModuleRecords = modules;
          this.blockUI.stop();
        },
        () => {
          this.blockUI.stop();
        }
      );
  }

  studentExists():boolean {
    // return this.studentInfo == null;
    return true;
  }

  canEmailResults(){
    return true;
  }

  emailResults(sendMarks :boolean){
    this.blockUI.start("Sending email...");
    this.academicRecordService.sendStudentAcademicQualificationEmail(this.studentInfo.studentNumber, this.selectedAcademicQualification.qualificationCode, sendMarks)
      .subscribe(
        (emailMessage:ErrorInfo) => {
          if(emailMessage){
            this.toaster.success('Info', emailMessage.message);
          }
          this.blockUI.stop();
        },
        () => {
          this.blockUI.stop();
        }
      );
    return true;
  }

  back(){
    this.searchCriteriaService.selectedQualification = null;
    this.searchCriteriaService.qualModuleResults = null;
    this.router.navigateByUrl('/academicRecord');
  }
}
