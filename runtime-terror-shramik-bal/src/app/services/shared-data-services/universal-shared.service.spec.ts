import { TestBed } from '@angular/core/testing';

import { UniversalSharedService } from './universal-shared.service';

describe('UniversalSharedService', () => {
  let service: UniversalSharedService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UniversalSharedService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
