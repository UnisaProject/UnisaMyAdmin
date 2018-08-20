import {AbstractMaterialDetailInfo} from "./abstract-material-detail-info";

export interface StudyMaterialDetailInfo extends AbstractMaterialDetailInfo{
  semester : string;
  description : string;
  implementationDate : string;
  webId : string;
  blockedStatus : boolean;
  shortDescription : string;
}
