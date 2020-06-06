import { TestBed } from '@angular/core/testing';

import { ShramikHttpService } from './shramik-http.service';

describe('ShramikHttpService', () => {
  let service: ShramikHttpService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ShramikHttpService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
