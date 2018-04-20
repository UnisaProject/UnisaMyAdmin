import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ExamTimetableSearchComponent } from './exam-timetable-search.component';

describe('ExamTimetableSearchComponent', () => {
  let component: ExamTimetableSearchComponent;
  let fixture: ComponentFixture<ExamTimetableSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ExamTimetableSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ExamTimetableSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
