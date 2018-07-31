import { Component, OnInit } from '@angular/core';
import {BlockUI, NgBlockUI} from 'ng-block-ui';
import {StudentInfo}from 'myadmin-lib';
import {AcademicModuleResultInfo,AcademicQualResultInfo} from '../../info-objects';

@Component({
  selector: 'unisa-display-qual-modules',
  templateUrl: './display-qual-modules.component.html',
  styleUrls: ['./display-qual-modules.component.scss']
})
export class DisplayQualModulesComponent implements OnInit {

  creditsOnly:string='N';

  isStudent :boolean=false;

  public studentInfo: StudentInfo;

  public academicModuleRecords: AcademicModuleResultInfo[] = [];

  public academicQualificationRecords: AcademicQualResultInfo;

  @BlockUI()
  private blockUI: NgBlockUI;

  constructor() { }

  ngOnInit() {
    this.blockUI.stop();
  }

  studentExists():boolean {
    return this.studentInfo ==null;
  }
}
