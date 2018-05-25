import { TestBed, inject } from '@angular/core/testing';

import { MyadminLibService } from './myadmin-lib.service';

describe('MyadminLibService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [MyadminLibService]
    });
  });

  it('should be created', inject([MyadminLibService], (service: MyadminLibService) => {
    expect(service).toBeTruthy();
  }));
});
