import { TestBed } from '@angular/core/testing';

import { LikeTweetService } from './like-tweet.service';

describe('LikeTweetService', () => {
  let service: LikeTweetService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LikeTweetService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
