import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {BlockUI, NgBlockUI} from 'ng-block-ui';
import {Router} from "@angular/router";
import {StudentInfo, StudentService}from 'myadmin-lib';
import {StudentAcademicQualificationRecordInfo} from '../../info-objects';
import {AcademicRecordService} from '../../services/academic-record.service';
import {SearchCriteriaService} from '../../services/search-criteria.service';

@Component({
  selector: 'unisa-display-qualifications',
  templateUrl: './display-qualifications.component.html',
  styleUrls: ['./display-qualifications.component.scss']
})
export class DisplayQualificationsComponent implements OnInit {

  isStudent :boolean=false;

  public studentInfo: StudentInfo;

  public academicQualificationRecords: StudentAcademicQualificationRecordInfo[] = [];

  studentInputForm: FormGroup;

  @BlockUI()
  private blockUI: NgBlockUI;

  constructor(private router :Router,
              private formBuilder: FormBuilder,
              private academicRecordService : AcademicRecordService,
              private searchCriteriaService : SearchCriteriaService,
              private studentService : StudentService) {
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
    // If there is no search criteria navigate back to the search screen
    if(this.searchCriteriaService.studentInfo === null || this.searchCriteriaService.academicQualResults === null){
      this.router.navigate(["acadHistoryInput"]);
      return;
    }
    this.studentInfo = this.searchCriteriaService.studentInfo;
    this.academicQualificationRecords = [...this.searchCriteriaService.academicQualResults];
  }

  viewModuleResults(selectedQual: StudentAcademicQualificationRecordInfo) {
    this.searchCriteriaService.selectedQualification = {...selectedQual};
    this.router.navigateByUrl('/academicRecordModules');
  }

  studentExists():boolean {
    return true; //this.studentInfo == null;
  }

  onSubmit() {
    this.blockUI.start("Loading qualifications...");
    this.studentService.getStudentByStudentNumber(this.studentInputForm.value.studentNumber).subscribe((studentInfo:StudentInfo)=> {
      // Copy the data to the service
      this.searchCriteriaService.studentInfo = {...studentInfo};
      this.studentInfo = {...studentInfo};

      this.academicRecordService.getStudentAcademicQualificationResults(studentInfo.studentNumber).subscribe((studentAcadQualInfos:StudentAcademicQualificationRecordInfo[])=> {
        this.searchCriteriaService.academicQualResults = [...studentAcadQualInfos];
        this.academicQualificationRecords = [...studentAcadQualInfos];
        this.blockUI.stop();
      }, (error)=> {
        this.blockUI.stop();
      });
    }, (error)=> {
      this.blockUI.stop();
    });

  }

}
