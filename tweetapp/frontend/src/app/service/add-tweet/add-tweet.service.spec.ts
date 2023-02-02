import { TestBed } from '@angular/core/testing';

import { AddTweetService } from './add-tweet.service';

describe('AddTweetService', () => {
  let service: AddTweetService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AddTweetService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
