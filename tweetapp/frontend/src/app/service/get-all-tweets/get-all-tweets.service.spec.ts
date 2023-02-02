import { TestBed } from '@angular/core/testing';

import { GetAllTweetsService } from './get-all-tweets.service';

describe('GetAllTweetsService', () => {
  let service: GetAllTweetsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GetAllTweetsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
