import { DescriptionInfo } from './shared';

export class ExamPeriodInfo {
    code: number;
    descriptionInfo: DescriptionInfo[];
    examYear: number;
    examType: string;

    constructor(code?: number,
        descriptionInfo?: DescriptionInfo[],
        examYear?: number,
        examType?: string) {
        this.code = code;
        this.descriptionInfo = descriptionInfo;
        this.examYear = examYear;
        this.examType = examType;
    }
}
