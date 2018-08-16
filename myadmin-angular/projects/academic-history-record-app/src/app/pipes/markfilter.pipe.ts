import { Pipe, PipeTransform } from '@angular/core';

/**
 * Filter student marks by parsed in limit.
 */
@Pipe({
  name: 'markfilter'
})
export class MarkfilterPipe implements PipeTransform {

  transform(items: any[], filterValue: any): any {
    if (!items || !filterValue) {
      return items;
    }
    return items.filter(item => item.mark >= filterValue);
  }
}
