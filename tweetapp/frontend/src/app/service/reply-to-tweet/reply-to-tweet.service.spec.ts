import { TestBed } from '@angular/core/testing';

import { ReplyToTweetService } from './reply-to-tweet.service';

describe('ReplyToTweetService', () => {
  let service: ReplyToTweetService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ReplyToTweetService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
