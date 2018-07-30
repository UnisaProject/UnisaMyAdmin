import { Component, OnInit } from '@angular/core';
import {BlockUI, NgBlockUI} from 'ng-block-ui';
import {Router} from "@angular/router";
import {StudyMaterialFormService, StudyMaterialService} from '../../services';
import {StudentInfo} from 'myadmin-lib';
import {ModuleEnrolmentInfo} from '../../info-objects';

@Component({
  selector: 'unisa-student-courselist',
  templateUrl: './student-courselist.component.html',
  styleUrls: ['./student-courselist.component.scss']
})
export class StudentCourselistComponent implements OnInit {
  /**
   * Date right now
   */
  public today: Date;

  public studentInfo: StudentInfo;

  public studentModules: ModuleEnrolmentInfo[] = [];

  @BlockUI()
  private blockUI: NgBlockUI;

  constructor(private studyMaterialFormService : StudyMaterialFormService,
              private studyMaterialService : StudyMaterialService,
              private router: Router) { }

  ngOnInit() {
    this.blockUI.stop();
    // If there is no search criteria navigate back to the search screen
    if(this.studyMaterialFormService.studentInfo === null || this.studyMaterialFormService.studentModuleEnrolmentList === null){
      this.router.navigate(["search"]);
      return;
    }
    this.today = new Date();
    this.studentInfo = this.studyMaterialFormService.studentInfo;
    this.studentModules = [...this.studyMaterialFormService.studentModuleEnrolmentList];

  }

  viewMaterial(studentModule: ModuleEnrolmentInfo) {
    this.router.navigate(['/viewCourse', studentModule.studyUnitCode, studentModule.academicYear, studentModule.semesterPeriod]);
  }

  cancel() {
    this.studyMaterialFormService.studentModuleEnrolmentList = null;
    this.studyMaterialFormService.studentInfo = null;
    this.studyMaterialFormService.studentModuleMaterialList = null;
    this.router.navigateByUrl('/search');
  }

}
