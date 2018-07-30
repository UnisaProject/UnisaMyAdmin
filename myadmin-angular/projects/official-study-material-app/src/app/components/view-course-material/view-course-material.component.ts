import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Subscription} from "rxjs/Subscription";
import {StudyMaterialDetailInfo, StudyMaterialService} from "myadmin-lib";
import {DatePipe} from "@angular/common";
import {BlockUI, NgBlockUI} from "ng-block-ui";

@Component({
  selector: 'unisa-view-course-material',
  templateUrl: './view-course-material.component.html',
  styleUrls: ['./view-course-material.component.scss']
})
export class ViewCourseMaterialComponent implements OnInit, OnDestroy {

  /**
   * Reference to blockUI
   */
  @BlockUI()
  private blockUI: NgBlockUI;

  academicYear:number;
  moduleCode:string;
  semesterCode:string;
  listDate = '';

  private routeParamsSubscription:Subscription;

  public moduleStudyMaterialList:StudyMaterialDetailInfo[] = [];

  constructor(private route:ActivatedRoute,
              private studyMaterialService: StudyMaterialService,
              private datePipe: DatePipe) {

  }

  private getCourseMaterial(){
    this.studyMaterialService.requestModuleStudyMaterials(
      this.moduleCode,
      this.academicYear,
      this.semesterCode)
      .subscribe((studyMaterial) => {
        this.moduleStudyMaterialList = studyMaterial;
        this.blockUI.stop();
      }, () => {
        this.blockUI.stop();
      });
  }

  /**
   * Method to split the full course ID to academic year, module and semester.
   * @param {string} lmsCourseId
   */
  private splitLmsCourseId(lmsCourseId: string): void {
    // Given LCR4803-18-S1
    this.academicYear = +('20' + lmsCourseId.substr(8, 2)); // 2018
    this.moduleCode = lmsCourseId.substr(0, 7); // LCR4803
    this.semesterCode = lmsCourseId.substr(11, 2); // S1
  }

  ngOnInit() {
    this.blockUI.start("Loading study material...");
    this.listDate = this.datePipe.transform(new Date(), 'yyyy-MM-dd');

    this.routeParamsSubscription = this.route.params.subscribe((params) =>{
      const lmsSiteId = params['lmsCourseId'];
      this.splitLmsCourseId(lmsSiteId);
      this.getCourseMaterial();
    })
  }

  ngOnDestroy(): void {
    if(this.routeParamsSubscription !== null){
      this.routeParamsSubscription.unsubscribe();
    }
  }

}
