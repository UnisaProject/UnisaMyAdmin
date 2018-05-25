import { Pipe, PipeTransform } from '@angular/core';
import {DescriptionInfo} from "../info-objects/shared";

/**
 * Filter to get the description of a list of DescriptionInfo objects that matches
 * the request locale.
 */
@Pipe({
  name: 'description'
})
export class DescriptionPipe implements PipeTransform {

  transform(value: DescriptionInfo[], locale: string): string {
    const descriptionInfo:DescriptionInfo = value.filter(descriptionInfo => {
      return descriptionInfo.locale === locale
    })[0];
    return descriptionInfo === null ? '' : descriptionInfo.description;
  }

}
