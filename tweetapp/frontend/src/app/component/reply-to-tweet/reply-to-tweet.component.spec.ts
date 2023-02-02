import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReplyToTweetComponent } from './reply-to-tweet.component';

describe('ReplyToTweetComponent', () => {
  let component: ReplyToTweetComponent;
  let fixture: ComponentFixture<ReplyToTweetComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReplyToTweetComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReplyToTweetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
