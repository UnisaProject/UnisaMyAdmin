import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {BlockUI, NgBlockUI} from 'ng-block-ui';
import {Router} from "@angular/router";
import {ErrorInfo, StudentInfo, ToasterNotificationService}from 'myadmin-lib';
import {AcademicRecordEmailRequestInfo, AcademicModuleRecordInfo,StudentAcademicQualificationRecordInfo} from '../../info-objects';
import {AcademicRecordService} from '../../services/academic-record.service';
import {AcademicRecordModuleService} from '../../services/academic-record-module.service';
import {SearchCriteriaService} from '../../services/search-criteria.service';
import {MarkfilterPipe} from '../../pipes/markfilter.pipe';

@Component({
  selector: 'unisa-display-qual-modules',
  templateUrl: './display-qual-modules.component.html',
  styleUrls: ['./display-qual-modules.component.scss'],
  providers : [MarkfilterPipe]
})
export class DisplayQualModulesComponent implements OnInit {

  academicModulesForm: FormGroup;

  isStudent :boolean=false;

  emailRequestInfo :AcademicRecordEmailRequestInfo;

  public studentInfo: StudentInfo;

  public academicModuleRecords: AcademicModuleRecordInfo[] = [];

  public selectedAcademicQualification : StudentAcademicQualificationRecordInfo;

  @BlockUI()
  private blockUI: NgBlockUI;

  constructor(private formBuilder: FormBuilder,
              private router :Router,
              private academicRecordModuleService : AcademicRecordModuleService,
              private academicRecordService : AcademicRecordService,
              private searchCriteriaService : SearchCriteriaService,
              private toaster : ToasterNotificationService,
              private markfilterPipe: MarkfilterPipe) {
    this.studentInfo = {...this.searchCriteriaService.studentInfo};
    this.selectedAcademicQualification = {...this.searchCriteriaService.selectedQualification};
    this.academicModulesForm = this.formBuilder.group({
      creditsOnly : ["N"]
    });
  }

  ngOnInit() {
    this.blockUI.stop();
    if(this.searchCriteriaService.studentInfo === null || this.searchCriteriaService.selectedQualification === null){
      this.router.navigate(["academicRecord"]);
      return;
    }
   // this.studentInfo = {...this.searchCriteriaService.studentInfo};
    //this.selectedAcademicQualification = {...this.searchCriteriaService.selectedQualification};
    this.getAcademicRecordModuleList();
  }

  private getAcademicRecordModuleList():void {
    this.blockUI.start("Loading modules...");
    this.academicRecordModuleService.getStudentAcademicModuleResults(this.studentInfo.studentNumber, this.selectedAcademicQualification.qualificationCode, false)
      .subscribe(
        (modules:AcademicModuleRecordInfo[]) => {
          this.searchCriteriaService.qualModuleResults = [...modules];
          this.academicModuleRecords = [...modules];
          this.blockUI.stop();
        },
        () => {
          this.blockUI.stop();
        }
      );
  }

  studentExists():boolean {
    // TODO replace with role check from CAS etc
    return true;
  }

  canEmailResults(){
    if(this.selectedAcademicQualification) {
      return this.selectedAcademicQualification.academicRequestEmailFlag === 'E';
    }
    return false;
  }

  /**
   * Update list according view selector 
   * @param event
   */
  filterMarks(event) :void{
    if(event.target.value && event.target.value === 'Y'){
      this.academicModuleRecords = this.markfilterPipe.transform(this.academicModuleRecords, 50);
    }else{
      this.academicModuleRecords = [...this.searchCriteriaService.qualModuleResults];
    }
  }

  emailResults(sendMarks :boolean){
    this.blockUI.start("Sending email...");
    this.emailRequestInfo.studentNumber = this.studentInfo.studentNumber;
    this.emailRequestInfo.academicQualificationCode = this.selectedAcademicQualification.qualificationCode;
    this.emailRequestInfo.isAttachMarks = sendMarks;
    this.academicRecordService.sendStudentAcademicQualificationEmail(this.emailRequestInfo)
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
