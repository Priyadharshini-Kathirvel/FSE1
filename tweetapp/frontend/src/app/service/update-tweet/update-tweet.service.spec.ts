import { TestBed } from '@angular/core/testing';

import { UpdateTweetService } from './update-tweet.service';

describe('UpdateTweetService', () => {
  let service: UpdateTweetService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UpdateTweetService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
