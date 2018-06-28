import {TrackAndTraceRecordInfo} from './';
import {StudentInfo} from "myadmin-lib";

export interface ParcelTrackingInfo {
  
  traceRecordInfoList:TrackAndTraceRecordInfo[];

  studentuser?:boolean;

  studentInfo:StudentInfo;

  notvalid?:boolean;
}
