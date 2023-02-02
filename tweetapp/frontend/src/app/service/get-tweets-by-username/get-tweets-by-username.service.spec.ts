import { TestBed } from '@angular/core/testing';

import { GetTweetsByUsernameService } from './get-tweets-by-username.service';

describe('GetTweetsByUsernameService', () => {
  let service: GetTweetsByUsernameService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GetTweetsByUsernameService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
