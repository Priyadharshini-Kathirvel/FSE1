import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetTweetsByUsernameComponent } from './get-tweets-by-username.component';

describe('GetTweetsByUsernameComponent', () => {
  let component: GetTweetsByUsernameComponent;
  let fixture: ComponentFixture<GetTweetsByUsernameComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GetTweetsByUsernameComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GetTweetsByUsernameComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
