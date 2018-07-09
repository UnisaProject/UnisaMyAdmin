import {Component, OnInit} from "@angular/core";
import {BlockUI, NgBlockUI} from "ng-block-ui";
import {StudyMaterialFormService, StudyMaterialService} from "../../services/";
import {StudyMaterialDetailInfo} from "../../info-objects/";
import {ActivatedRoute, Router} from "@angular/router";
import "rxjs/add/operator/map";
import {StudentInfo} from "myadmin-lib";

@Component({
  selector: 'unisa-view-course-material',
  templateUrl: './view-course-material.component.html',
  styleUrls: ['./view-course-material.component.scss']
})
export class ViewCourseMaterialComponent implements OnInit {

  @BlockUI()
  private blockUI:NgBlockUI;

  public studentInfo:StudentInfo;
  public moduleStudyMaterialList:StudyMaterialDetailInfo[] = [];

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
    });
    this.studentInfo = this.studyMaterialFormService.studentInfo;
    this.getModuleStudyMaterialList();
  }

  private getModuleStudyMaterialList():void {
    this.blockUI.start("Loading study material...");
    this.studyMaterialService.requestModuleStudyMaterials(this.moduleCode, this.academicYear, this.semesterCode)
      .subscribe(
        (moduleMaterials:StudyMaterialDetailInfo[]) => {
          this.moduleStudyMaterialList = moduleMaterials;
          this.blockUI.stop();
        },
        () => {
          // this.router.navigate(["search"]);
          this.blockUI.stop();
        }
      );
  }

  downloadMaterial(moduleMaterial:StudyMaterialDetailInfo) {
    this.blockUI.start("Downloading study material...");
    this.studyMaterialService.downloadMaterial(moduleMaterial)
      .subscribe(
        (data) => {
          var type = 'application/pdf';
          const blob = new Blob([data], { type });
          const url = window.URL.createObjectURL(blob);
          // create hidden dom element (so it works in all browsers)
          const a = document.createElement('a');
          a.setAttribute('style', 'display:none;');
          document.body.appendChild(a);
          // create file, attach to hidden element and open hidden element
          a.href = url;
          a.download = moduleMaterial.shortDescription+'.pdf';
          a.click();
          this.blockUI.stop();
          return url;
        },
        () => {
          this.blockUI.stop();
        }
      );
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }

  back() {
    this.studyMaterialFormService.studentModuleMaterialList = null;
    this.studyMaterialFormService.selectedModule = null;
    this.router.navigateByUrl('/courses');
  }

  cancel() {
    this.studyMaterialFormService.studentModuleEnrolmentList = null;
    this.studyMaterialFormService.studentInfo = null;
    this.studyMaterialFormService.selectedModule = null;
    this.studyMaterialFormService.studentModuleMaterialList = null;
    this.router.navigateByUrl('/search');
  }

}
