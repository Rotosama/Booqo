import { TestBed } from '@angular/core/testing';

import { SetupcenterService } from './setupcenter.service';

describe('SetupcenterService', () => {
  let service: SetupcenterService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SetupcenterService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
