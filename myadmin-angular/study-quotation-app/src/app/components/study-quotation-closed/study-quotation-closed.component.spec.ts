import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StudyQuotationClosedComponent } from './study-quotation-closed.component';

describe('StudyQuotationClosedComponent', () => {
  let component: StudyQuotationClosedComponent;
  let fixture: ComponentFixture<StudyQuotationClosedComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StudyQuotationClosedComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StudyQuotationClosedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
