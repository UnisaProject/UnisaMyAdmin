
export class StudyQuotationInfo {
    academicYear: number;
    countryCode: number;
    qualificationType: number;
    qualificationCode: number;
    libraryCard: string;
    matricExemption: string;

    constructor(academicYear?: number,
                countryCode?: number,
                qualificationType?: number,
                qualificationCode?: number,
                libraryCard?: string,
      matricExemption?: string) {
        this.academicYear = academicYear;
        this.countryCode = countryCode;
        this.qualificationType = qualificationType;
        this.qualificationCode = qualificationCode;
        this.libraryCard = libraryCard;
        this.matricExemption = matricExemption;
    }
}
