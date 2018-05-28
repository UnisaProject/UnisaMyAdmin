/**
 * A class representing an exam period
 */
import {DescriptionInfo} from "myadmin-lib";

export interface ExamPeriodInfo {
    code: number;
    descriptionInfo: DescriptionInfo[];
    examYear: number;
    examType: string;
}
