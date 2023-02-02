import { TestBed } from '@angular/core/testing';

import { LoginLandingPageService } from './login-landing-page.service';

describe('LoginLandingPageService', () => {
  let service: LoginLandingPageService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LoginLandingPageService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
