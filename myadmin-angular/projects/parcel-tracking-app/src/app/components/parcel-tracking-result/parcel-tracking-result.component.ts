import { Component, OnInit } from '@angular/core';
import {BlockUI, NgBlockUI} from "ng-block-ui";

@Component({
  selector: 'unisa-parcel-tracking-result',
  templateUrl: './parcel-tracking-result.component.html',
  styleUrls: ['./parcel-tracking-result.component.scss']
})
export class ParcelTrackingResultComponent implements OnInit {

  @BlockUI()
  private blockUI:NgBlockUI;

  constructor() {
    this.blockUI.start("Loading quote...");
  }

  ngOnInit() {
    this.blockUI.stop();
  }

}
