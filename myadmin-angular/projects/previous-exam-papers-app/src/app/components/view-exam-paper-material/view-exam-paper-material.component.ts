import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Subscription} from "rxjs/Subscription";
import {DatePipe} from "@angular/common";
import {BlockUI, NgBlockUI} from "ng-block-ui";
import {ExamPaperMaterialInfo} from "myadmin-lib";
import {ExamPaperMaterialService} from "../../services/exam-paper-material.service";

@Component({
  selector: 'unisa-view-exam-paper-material',
  templateUrl: './view-exam-paper-material.component.html',
  styleUrls: ['./view-exam-paper-material.component.scss']
})
export class ViewExamPaperMaterialComponent implements OnInit, OnDestroy {

  /**
   * Reference to blockUI
   */
  @BlockUI()
  private blockUI: NgBlockUI;

  moduleCode:string;
  listDate = '';

  private routeParamsSubscription:Subscription;

  public examPaperMaterials:ExamPaperMaterialInfo[] = [];

  constructor(private route:ActivatedRoute,
              private examPaperMaterialService: ExamPaperMaterialService,
              private datePipe: DatePipe) {

  }

  private getCourseMaterial(){
    this.examPaperMaterialService.getExamPaperMaterial(
      this.moduleCode)
      .subscribe((examPaperMaterials) => {
        this.examPaperMaterials = examPaperMaterials;
        this.blockUI.stop();
      }, () => {
        this.blockUI.stop();
      });
  }

  ngOnInit() {
    this.blockUI.start("Loading study material...");
    this.listDate = this.datePipe.transform(new Date(), 'yyyy-MM-dd');
    this.routeParamsSubscription = this.route.params.subscribe((params) =>{
      this.moduleCode = params['moduleCode'];
      this.getCourseMaterial();
    })
  }

  ngOnDestroy(): void {
    if(this.routeParamsSubscription !== null){
      this.routeParamsSubscription.unsubscribe();
    }
  }

}
