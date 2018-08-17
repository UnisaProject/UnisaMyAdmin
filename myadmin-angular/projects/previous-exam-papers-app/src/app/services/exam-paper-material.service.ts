import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient, HttpParams} from "@angular/common/http";
import {ExamPaperMaterialInfo} from "myadmin-lib";

@Injectable({
  providedIn: 'root'
})
export class ExamPaperMaterialService {

  constructor(private http: HttpClient) {
  }

  getExamPapers(courseCode: string): Observable<ExamPaperMaterialInfo[]> {
    let params = new HttpParams().set('courseCode', courseCode);
    return this.http.get<ExamPaperMaterialInfo[]>('/myadmin-exam-services/services/rest/exampapermaterialservice/exampapermaterial', { params });
  }
}
