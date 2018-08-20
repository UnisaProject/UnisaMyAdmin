import {AbstractMaterialDetailInfo} from "./abstract-material-detail-info";

export interface ExamPaperMaterialInfo extends AbstractMaterialDetailInfo{
  periodDesc: string;

  /**
   * The language in which this paper is.
   */
  language: string;
}
