import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StudyQuotationResultComponent } from './study-quotation-result.component';

describe('StudyQuotationResultComponent', () => {
  let component: StudyQuotationResultComponent;
  let fixture: ComponentFixture<StudyQuotationResultComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StudyQuotationResultComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StudyQuotationResultComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
