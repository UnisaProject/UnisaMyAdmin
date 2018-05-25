import { TestBed, inject } from '@angular/core/testing';

import { ExamPeriodService } from './exam-period.service';

describe('ExamPeriodService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ExamPeriodService]
    });
  });

  it('should be created', inject([ExamPeriodService], (service: ExamPeriodService) => {
    expect(service).toBeTruthy();
  }));
});
