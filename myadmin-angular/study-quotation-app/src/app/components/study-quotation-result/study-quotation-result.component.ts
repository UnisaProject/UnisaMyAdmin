import { Component, OnInit } from '@angular/core';
import {BlockUI, NgBlockUI} from "ng-block-ui";

@Component({
  selector: 'app-study-quotation-result',
  templateUrl: './study-quotation-result.component.html',
  styleUrls: ['./study-quotation-result.component.css']
})
export class StudyQuotationResultComponent implements OnInit {

  @BlockUI()
  private blockUI: NgBlockUI;

  constructor() { }

  ngOnInit() {
    this.blockUI.stop();
  }

}
