import { DescriptionInfo } from './shared';

export class ExamPeriodInfo {
    code: number;
    description: DescriptionInfo;
    shortDescription: DescriptionInfo;
    examYear: number;
    examType: string;

    constructor(code?: number,
        description?: DescriptionInfo,
        shortDescription?: DescriptionInfo,
        examYear?: number,
        examType?: string) {
        this.code = code;
        this.description = description;
        this.shortDescription = shortDescription;
        this.examYear = examYear;
        this.examType = examType;
    }
}