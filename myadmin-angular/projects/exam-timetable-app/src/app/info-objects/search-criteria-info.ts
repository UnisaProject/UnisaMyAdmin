import { ExamPeriodInfo } from './exam-period-info';

/**
 * A class representing the criteria to search for an exam timetable
 */
export interface SearchCriteriaInfo {
    year: number;
    examPeriod: ExamPeriodInfo;
    courseCodes: string[];
}
