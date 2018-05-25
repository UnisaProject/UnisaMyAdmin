import { TestBed, inject } from '@angular/core/testing';

import { ExamPeriodDateService } from './exam-period-date.service';

describe('ExamPeriodDateService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ExamPeriodDateService]
    });
  });

  it('should be created', inject([ExamPeriodDateService], (service: ExamPeriodDateService) => {
    expect(service).toBeTruthy();
  }));
});
