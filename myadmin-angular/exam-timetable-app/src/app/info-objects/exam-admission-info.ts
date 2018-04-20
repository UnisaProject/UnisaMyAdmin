export class ExamAdmissionInfo {
    year: number;
    examPeriodCode: number;
    admissionDone: boolean;
    examType: number;
    examArrangement: boolean;

    constructor(year?: number, examPeriodCode?: number, admissionDone?: boolean, examType?: number, examArrangement?: boolean) {
        this.year = year;
        this.examPeriodCode = examPeriodCode;
        this.admissionDone = admissionDone;
        this.examType = examType;
        this.examArrangement = examArrangement;
    }
}