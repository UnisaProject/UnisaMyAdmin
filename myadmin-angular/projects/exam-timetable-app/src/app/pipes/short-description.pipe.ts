import { Pipe, PipeTransform } from '@angular/core';
import {DescriptionInfo} from "myadmin-lib";

/**
 * Filter to get the short description of a list of DescriptionInfo objects that matches
 * the request locale.
 */
@Pipe({
  name: 'shortDescription'
})
export class ShortDescriptionPipe implements PipeTransform {

  transform(value: DescriptionInfo[], locale: string): string {
    const descriptionInfo:DescriptionInfo = value.filter(descriptionInfo => {
      return descriptionInfo.locale === locale
    })[0];
    return descriptionInfo === null ? '' : descriptionInfo.shortDescription;
  }

}
