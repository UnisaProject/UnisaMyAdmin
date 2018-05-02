import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StudyQuotationSearchComponent } from './study-quotation-search.component';

describe('StudyQuotationSearchComponent', () => {
  let component: StudyQuotationSearchComponent;
  let fixture: ComponentFixture<StudyQuotationSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StudyQuotationSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StudyQuotationSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
