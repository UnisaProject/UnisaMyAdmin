import {Injectable} from "@angular/core";
import {StudentInfo} from "myadmin-lib";

@Injectable()
export class SearchCriteriaService {
  studentInfo: StudentInfo = null;
  constructor() {
  }
}
