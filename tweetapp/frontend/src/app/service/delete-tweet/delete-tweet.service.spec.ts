import { TestBed } from '@angular/core/testing';

import { DeleteTweetService } from './delete-tweet.service';

describe('DeleteTweetService', () => {
  let service: DeleteTweetService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DeleteTweetService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
