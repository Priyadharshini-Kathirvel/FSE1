import { TestBed } from '@angular/core/testing';

import { SearchUserByUsernameService } from './search-user-by-username.service';

describe('SearchUserByUsernameService', () => {
  let service: SearchUserByUsernameService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SearchUserByUsernameService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
