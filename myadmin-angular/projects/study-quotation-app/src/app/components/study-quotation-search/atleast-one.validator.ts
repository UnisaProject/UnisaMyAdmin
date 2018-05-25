import {FormArray, FormGroup, ValidationErrors, ValidatorFn} from '@angular/forms';
import * as isEmpty from 'lodash.isempty';
import * as isString from 'lodash.isstring';
/**
 * Validator to check that atleast one course code is entered
 * @returns {ValidatorFn}
 */
export function atleastOneCourseCode(): ValidatorFn {
  return function (g: FormGroup): ValidationErrors{
    // Get the subject codes form array
    const courseCodesForm: FormArray = <FormArray>g.get('courseCodes');
    let atleastOne = false;
    courseCodesForm.controls.forEach(control => {
      const value:string = control.value;
      if(isString(value) && !isEmpty(value.trim())){
        atleastOne = true;
      };
    });
    return atleastOne ? null : {'atleastOne': true};
  };
}
