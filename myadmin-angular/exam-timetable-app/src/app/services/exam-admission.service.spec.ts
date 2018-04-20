import { TestBed, inject } from '@angular/core/testing';

import { ExamAdmissionService } from './exam-admission.service';

describe('ExamAdmissionService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ExamAdmissionService]
    });
  });

  it('should be created', inject([ExamAdmissionService], (service: ExamAdmissionService) => {
    expect(service).toBeTruthy();
  }));
});
