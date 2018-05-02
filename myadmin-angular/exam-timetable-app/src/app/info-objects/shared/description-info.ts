export class DescriptionInfo {
  locale: string;
  shortDescription: string;
  description: string;

    constructor(locale: string, shortDescription: string, description: string) {
        this.locale = locale;
        this.shortDescription = shortDescription;
        this.description = description;
    }
}
