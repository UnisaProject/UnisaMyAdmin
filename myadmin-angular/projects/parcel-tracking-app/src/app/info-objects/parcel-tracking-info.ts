import {StudentInfo, TrackAndTraceRecordInfo} from './';

export interface ParcelTrackingInfo {
  
  traceRecordInfoList:TrackAndTraceRecordInfo[];

  studentuser?:boolean;

  studentInfo:StudentInfo;

  notvalid?:boolean;
}
