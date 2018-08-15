import {Component, OnInit} from "@angular/core";
import {Router} from "@angular/router";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AcademicRecordService} from "../../services/academic-record.service";
import {StudentAcademicQualificationRecordInfo} from "../../info-objects/student-academic-qualification-record-info";
import {SearchCriteriaService} from "../../services/search-criteria.service";
import {BlockUI, NgBlockUI} from "ng-block-ui";
import {StudentInfo, StudentService} from "myadmin-lib";

@Component({
  selector: 'unisa-acad-history-input',
  templateUrl: './acad-history-input.component.html',
  styleUrls: ['./acad-history-input.component.scss']
})
export class AcadHistoryInputComponent implements OnInit {

  studentInputForm:FormGroup;

  /**
   * Reference to blockUI
   */
  @BlockUI()
  private blockUI:NgBlockUI;

  constructor(private router:Router,
              private formBuilder:FormBuilder,
              private academicRecordService:AcademicRecordService,
              private searchCriteriaService:SearchCriteriaService,
              private studentService:StudentService) {
    this.initForm();
  }

  private initForm() {
    this.studentInputForm = this.formBuilder.group({
      studentNumber: [
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

  onSubmit() {
    this.blockUI.start("Loading Qualifications...");
    this.studentService.getStudentByStudentNumber(this.studentInputForm.value.studentNumber).subscribe((studentInfo:StudentInfo)=> {
      this.searchCriteriaService.studentInfo = {...studentInfo};
      this.academicRecordService.getStudentAcademicQualificationResults(studentInfo.studentNumber).subscribe((studentAcadQualInfos:StudentAcademicQualificationRecordInfo[])=> {
        this.searchCriteriaService.academicQualResults = [...studentAcadQualInfos];
        this.router.navigateByUrl('/academicRecord');
      }, (error)=> {
        this.blockUI.stop();
      });

    }, (error)=> {
      this.blockUI.stop();
    });

  }

}


