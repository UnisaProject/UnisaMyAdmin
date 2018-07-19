import {TrackingInfo} from '.';
import {StudentInfo} from "myadmin-lib";

export interface PackageInfo {
  
  trackings:TrackingInfo[];

  studentuser?:boolean;

  studentInfo:StudentInfo;

  notvalid?:boolean;
}
