import {FormGroup, ValidationErrors, ValidatorFn} from '@angular/forms';
import * as isEmpty from 'lodash.isempty';
import {ExamPeriodInfo} from "../../info-objects";
/**
 * Validator to check that atleast one course code is entered
 * @returns {ValidatorFn}
 */
export function selectedExamPeriod(): ValidatorFn {
  return function (g: FormGroup): ValidationErrors{
    const value:ExamPeriodInfo|null = g.value;

    const hasValue = !isEmpty(value) && value.hasOwnProperty('examType');

    return hasValue ? null : {'hasValue': true};
  };
}
