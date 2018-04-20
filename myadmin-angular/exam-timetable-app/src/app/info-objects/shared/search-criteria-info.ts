import { ExamPeriodInfo } from './../exam-period-info';

export class SearchCriteriaInfo {
    year: number;
    examPeriod: ExamPeriodInfo;
    courseCodes: string[];

    constructor(examPeriod?: ExamPeriodInfo, year?: number, ...courseCodes: string[]) {
        this.year = year;
        this.examPeriod = examPeriod;
        this.courseCodes = courseCodes;
    }
}