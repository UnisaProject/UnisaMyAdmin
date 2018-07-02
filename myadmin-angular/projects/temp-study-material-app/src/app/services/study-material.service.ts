import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import { Observable } from 'rxjs';
import { StudentModuleEnrolmentInfo } from '../info-objects';
import {StudentInfo} from 'myadmin-lib';

@Injectable()
export class StudyMaterialService {

  constructor(private http:HttpClient) {
  }

  requestStudentModuleEnrolments(studentInfo : StudentInfo): Observable<StudentModuleEnrolmentInfo[]> {
    return this.http.post<StudentModuleEnrolmentInfo[]>('/myadmin-student-services/studentservices/studymaterial/courselist', studentInfo);
  }
}
