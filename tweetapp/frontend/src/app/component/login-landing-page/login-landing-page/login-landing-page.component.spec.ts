import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginLandingPageComponent } from './login-landing-page.component';

describe('LoginLandingPageComponent', () => {
  let component: LoginLandingPageComponent;
  let fixture: ComponentFixture<LoginLandingPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LoginLandingPageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LoginLandingPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
