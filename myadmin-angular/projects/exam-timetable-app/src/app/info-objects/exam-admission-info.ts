/**
 * An interface representing an exam admission.
 */
export interface ExamAdmissionInfo {
    year: number;
    examPeriodCode: number;
    admissionDone: boolean;
    examType: number;
    examArrangement: boolean;
}
