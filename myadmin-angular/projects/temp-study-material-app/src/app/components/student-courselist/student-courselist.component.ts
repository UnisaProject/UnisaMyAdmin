import { Component, OnInit } from '@angular/core';
import {BlockUI, NgBlockUI} from 'ng-block-ui';
import {Router} from "@angular/router";
import {StudyMaterialFormService, StudyMaterialService} from '../../services';
import {StudentInfo} from 'myadmin-lib';
import {StudentModuleEnrolmentInfo} from '../../info-objects';

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

  public studentModules: StudentModuleEnrolmentInfo[] = [];

  @BlockUI()
  private blockUI: NgBlockUI;

  constructor(private studyMaterialFormService : StudyMaterialFormService,
              private studyMaterialService : StudyMaterialService,
              private router: Router) { }

  ngOnInit() {
    this.blockUI.start();
    // If there is no search criteria navigate back to the search screen
    if(this.studyMaterialFormService.studentInfo === null){
      this.router.navigate(["search"]);
      return;
    }
    this.today = new Date();
    this.studentInfo = this.studyMaterialFormService.studentInfo;
    this.getStudentModuleList(this.studyMaterialFormService.studentInfo);
  }

  private getStudentModuleList(searchCriteria: StudentInfo): void {
    this.studyMaterialService.requestStudentModuleEnrolments(searchCriteria)
      .subscribe(
        (examinations: StudentModuleEnrolmentInfo[]) => {
          this.studentModules = examinations;
          this.blockUI.stop()
        },
        () => {
          this.blockUI.stop()
        }
      );
  }

}
