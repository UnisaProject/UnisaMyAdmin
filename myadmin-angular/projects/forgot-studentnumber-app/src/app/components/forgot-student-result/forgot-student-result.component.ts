import { Component, OnInit } from '@angular/core';
import {SearchCriteriaService} from "../../services/search-criteria.service";
import {BlockUI, NgBlockUI} from "ng-block-ui";
import {Router} from "@angular/router";
import {StudentInfo} from "myadmin-lib";

@Component({
  selector: 'unisa-forgot-student-result',
  templateUrl: './forgot-student-result.component.html',
  styleUrls: ['./forgot-student-result.component.scss']
})
export class ForgotStudentResultComponent implements OnInit {
  /**
   * Reference to blockUI
   */
  @BlockUI()
  private blockUI: NgBlockUI;

  studentInfo:StudentInfo;

  constructor(private searchCriteriaService: SearchCriteriaService,
              private router: Router) { }

  ngOnInit() {
    this.blockUI.start();
    // If there is no search criteria navigate back to the search screen
    if(this.searchCriteriaService.studentInfo === null){
      this.router.navigate(["search"]);
      return;
    }
    this.studentInfo = this.searchCriteriaService.studentInfo;
    this.blockUI.stop();
  }

  back() {
    this.searchCriteriaService.studentInfo === null;
    this.router.navigateByUrl("/search");
  }

}
