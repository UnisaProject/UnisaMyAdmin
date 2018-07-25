import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Subscription} from "rxjs/Subscription";
import {StudyMaterialDetailInfo, StudyMaterialService} from "myadmin-lib";

@Component({
  selector: 'unisa-view-course-material',
  templateUrl: './view-course-material.component.html',
  styleUrls: ['./view-course-material.component.scss']
})
export class ViewCourseMaterialComponent implements OnInit, OnDestroy {

  academicYear:number;
  moduleCode:string;
  semesterCode:string;

  private routeParamsSubscription:Subscription;

  public moduleStudyMaterialList:StudyMaterialDetailInfo[] = [];

  constructor(private route:ActivatedRoute,
              private studyMaterialService: StudyMaterialService) {

  }

  private getCourseMaterial(){
    this.studyMaterialService.requestModuleStudyMaterials(
      this.moduleCode,
      this.academicYear,
      this.semesterCode)
      .subscribe((studyMaterial) =>{
        this.moduleStudyMaterialList = studyMaterial;
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
