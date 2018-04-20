import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ExamTimetableResultComponent } from './exam-timetable-result.component';

describe('ExamTimetableResultComponent', () => {
  let component: ExamTimetableResultComponent;
  let fixture: ComponentFixture<ExamTimetableResultComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ExamTimetableResultComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ExamTimetableResultComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
