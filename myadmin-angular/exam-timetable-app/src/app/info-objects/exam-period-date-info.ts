export class ExamPeriodDateInfo {

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

    constructor(year?: number,
        examPeriodCode?: number,
        courseCode?: string,
        paperNumber?: number,
        examDate?: Date,
        examTime?: Date,
        storeShelfNumber?: string,
        strongRoomShelf?: string,
        markReadingCode?: string,
        resultReleaseDate?: Date,
        scriptMarkTotal?: number,
        yearMarkTotal?: number,
        markReadTotal?: number,
        paperMarkTotal?: number,
        blockAdjustment?: number,
        markReadAdjustment?: number,
        releaseTime?: Date,
        lastUser?: string,
        lastProgram?: string,
        noOfQuestions?: number,
        changeReason?: string,
        comment?: string,
        paperWeight?: number,
        paperSubMin?: number,
        paperSubMinSuppl?: string,
        durationDays?: number,
        durationHours?: number,
        durationMinutes?: number,
        noOfPages?: number,
        paperType?: string,
        electiveType?: string) {
            this.year = year;
            this.examPeriodCode = examPeriodCode;
            this.courseCode = courseCode;
            this.paperNumber = paperNumber;
            this.examDate = examDate;
            this.examTime = examTime;
            this.storeShelfNumber = storeShelfNumber;
            this.strongRoomShelf = strongRoomShelf;
            this.markReadingCode = markReadingCode;
            this.resultReleaseDate = resultReleaseDate;
            this.scriptMarkTotal = scriptMarkTotal;
            this.yearMarkTotal = yearMarkTotal;
            this.markReadTotal = markReadTotal;
            this.paperMarkTotal = paperMarkTotal;
            this.blockAdjustment = blockAdjustment;
            this.markReadAdjustment = markReadAdjustment;
            this.releaseTime = releaseTime;
            this.lastUser = lastUser;
            this.lastProgram = lastProgram;
            this.noOfQuestions = noOfQuestions;
            this.changeReason = changeReason;
            this.comment = comment;
            this.paperWeight = paperWeight;
            this.paperSubMin = paperSubMin;
            this.paperSubMinSuppl = paperSubMinSuppl;
            this.durationDays = durationDays;
            this.durationHours = durationHours;
            this.durationMinutes = durationMinutes;
            this.noOfPages = noOfPages;
            this.paperType = paperType;
            this.electiveType = electiveType;
    }
}