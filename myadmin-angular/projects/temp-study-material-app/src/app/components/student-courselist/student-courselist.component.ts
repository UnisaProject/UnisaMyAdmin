import { Component, OnInit } from '@angular/core';
import {BlockUI, NgBlockUI} from "ng-block-ui";

@Component({
  selector: 'unisa-student-courselist',
  templateUrl: './student-courselist.component.html',
  styleUrls: ['./student-courselist.component.scss']
})
export class StudentCourselistComponent implements OnInit {

  @BlockUI()
  private blockUI: NgBlockUI;

  constructor() { }

  ngOnInit() {
    this.blockUI.stop();
  }

}
