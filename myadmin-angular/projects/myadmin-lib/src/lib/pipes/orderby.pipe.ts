import { Pipe, PipeTransform } from '@angular/core';

@Pipe({ name: 'orderBy' })
export class OrderByPipe implements PipeTransform {

    transform(records: Array<any>, field?: any): Array<any> {
        if (records !== undefined) {
            return records.sort(function (a, b) {
                if (a[field] < b[field]) {
                    return -1;
                }
                else if (a[field] > b[field]) {
                    return 1;
                }
                else {
                    return 0;
                }
            });
        }
    };

}