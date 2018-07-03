import {Component, OnInit} from "@angular/core";
import {BlockUI, NgBlockUI} from "ng-block-ui";
import {StudyMaterialFormService, StudyMaterialService} from "../../services/";
import {StudyMaterialDetailInfo} from "../../info-objects/";
import {ActivatedRoute, Router} from "@angular/router";
import "rxjs/add/operator/map";
import {StudentInfo} from 'myadmin-lib';

@Component({
  selector: 'unisa-view-course-material',
  templateUrl: './view-course-material.component.html',
  styleUrls: ['./view-course-material.component.scss']
})
export class ViewCourseMaterialComponent implements OnInit {

  @BlockUI()
  private blockUI:NgBlockUI;

  public studentInfo: StudentInfo;
  public moduleStudyMaterialList: StudyMaterialDetailInfo[] = [];

  academicYear:number;
  moduleCode:string;
  semesterCode:string;
  private sub:any;

  constructor(private route:ActivatedRoute,
              private router:Router,
              private studyMaterialFormService:StudyMaterialFormService,
              private studyMaterialService:StudyMaterialService) {
  }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      this.academicYear = +params['academicYear']; // (+) converts string 'id' to a number
      this.moduleCode = params['moduleCode'];
      this.semesterCode = params['semesterCode'];

      // In a real app: dispatch action to load the details here.
    });
    this.studentInfo = this.studyMaterialFormService.studentInfo;
    this.getModuleStudyMaterialList();
    // this.route.params.subscribe(p => this.setParameters(p && p['id']));
  }

  private getModuleStudyMaterialList(): void {
    this.blockUI.start("Loading study material...");
    this.studyMaterialService.requestModuleStudyMaterials(this.moduleCode, this.academicYear, this.semesterCode)
      .subscribe(
        (moduleMaterials: StudyMaterialDetailInfo[]) => {
          this.moduleStudyMaterialList = moduleMaterials;
          this.blockUI.stop()
        },
        () => {
          this.router.navigate(["search"]);
          this.blockUI.stop()
        }
      );

  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }

}
