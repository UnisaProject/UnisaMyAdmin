import { Component, OnInit } from '@angular/core';
import {BlockUI, NgBlockUI} from "ng-block-ui";
@Component({
  selector: 'app-study-quotation-closed',
  templateUrl: './study-quotation-closed.component.html',
  styleUrls: ['./study-quotation-closed.component.css']
})
export class StudyQuotationClosedComponent implements OnInit {
  @BlockUI()
  private blockUI:NgBlockUI;
  
  constructor() { }

  ngOnInit() {
    this.blockUI.stop();
  }

}
