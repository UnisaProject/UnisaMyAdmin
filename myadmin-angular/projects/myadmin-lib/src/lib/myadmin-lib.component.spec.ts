import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MyadminLibComponent } from './myadmin-lib.component';

describe('MyadminLibComponent', () => {
  let component: MyadminLibComponent;
  let fixture: ComponentFixture<MyadminLibComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MyadminLibComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MyadminLibComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
