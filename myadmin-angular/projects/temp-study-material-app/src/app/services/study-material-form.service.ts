import { Injectable } from '@angular/core';
import {StudentInfo} from 'myadmin-lib';

@Injectable()
export class StudyMaterialFormService {

  studentInfo:StudentInfo = null;

  constructor() { }
}
