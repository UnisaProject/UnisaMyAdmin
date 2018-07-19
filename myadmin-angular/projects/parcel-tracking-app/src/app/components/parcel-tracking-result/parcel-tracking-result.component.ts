import {Component, OnInit} from '@angular/core';
import {BlockUI, NgBlockUI} from "ng-block-ui";
import {TrackingService} from '../../services/tracking.service';
import {ActivatedRoute, Router} from '@angular/router';
import 'rxjs/add/operator/map';
import {PackageInfo} from '../../info-objects';

@Component({
  selector: 'unisa-parcel-tracking-result',
  templateUrl: './parcel-tracking-result.component.html',
  styleUrls: ['./parcel-tracking-result.component.scss']
})
export class ParcelTrackingResultComponent implements OnInit {

  public packageInfo:PackageInfo;

  public studentNumber:number;

  public errorMessage:string;

  @BlockUI()
  private blockUI:NgBlockUI;

  constructor(private route:ActivatedRoute,
              private router:Router,
              private trackingService:TrackingService) {
    this.blockUI.start("Parcel Tracking...");
  }

  ngOnInit() {
    this.route.params.subscribe(p => this.getStudentParcelTracking(p && p['id']));
  }

  private getStudentParcelTracking(userId:number): void{
    this.blockUI.start("Parcel Tracking...");
    this.studentNumber = userId;
    this.trackingService.trackPackageByStudent(this.studentNumber)
      .subscribe((packageInfo:PackageInfo) => {
        this.packageInfo = packageInfo;
        this.blockUI.stop();
      },
        response => {
          if(response.error instanceof Error){
            this.errorMessage = response.error.message;
          }
          // If it looks like a framework error
          else if(response.error && response.error.message){
            this.errorMessage = response.error.message;
          }
          else {
            console.log(response);
            this.errorMessage = response.message;
          }
          this.blockUI.stop();
          this.router.navigateByUrl("search");
        })
  }
}
