/**
 *
 */
export interface ExamPeriodDateInfo {

    year: number;
    examPeriodCode: number;
    courseCode: string;
    paperNumber: number;
    examDate: Date;
    examTime: Date;
    storeShelfNumber: string;
    strongRoomShelf: string;
    markReadingCode: string;
    resultReleaseDate: Date;
    scriptMarkTotal: number;
    yearMarkTotal: number;
    markReadTotal: number;
    paperMarkTotal: number;
    blockAdjustment: number;
    markReadAdjustment: number;
    releaseTime: Date;
    lastUser: string;
    lastProgram: string;
    noOfQuestions: number;
    changeReason: string;
    comment: string;
    paperWeight: number;
    paperSubMin: number;
    paperSubMinSuppl: string;
    durationDays: number;
    durationHours: number;
    durationMinutes: number;
    noOfPages: number;
    paperType: string;
    electiveType: string;
}
