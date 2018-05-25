import { DescriptionInfo } from './shared';

/**
 * A class representing an exam period
 */
export interface ExamPeriodInfo {
    code: number;
    descriptionInfo: DescriptionInfo[];
    examYear: number;
    examType: string;
}
