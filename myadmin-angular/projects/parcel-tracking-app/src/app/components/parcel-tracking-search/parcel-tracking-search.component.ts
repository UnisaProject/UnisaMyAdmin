import { Component, OnInit } from '@angular/core';
import {BlockUI, NgBlockUI} from "ng-block-ui";
import {Router} from '@angular/router';

@Component({
  selector: 'unisa-parcel-tracking-search',
  templateUrl: './parcel-tracking-search.component.html',
  styleUrls: ['./parcel-tracking-search.component.scss']
})
export class ParcelTrackingSearchComponent implements OnInit {

  public studentNumber:number;

  @BlockUI()
  private blockUI:NgBlockUI;

  constructor(private router:Router) {
    this.blockUI.start();
  }

  ngOnInit() {
    this.blockUI.stop();
  }

  onSubmit() {
    this.blockUI.start("Parcel Tracking...");

    this.router.navigate(["result"]);
  }
}
