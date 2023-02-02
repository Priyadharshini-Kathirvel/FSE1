import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchUserByUsernameComponent } from './search-user-by-username.component';

describe('SearchUserByUsernameComponent', () => {
  let component: SearchUserByUsernameComponent;
  let fixture: ComponentFixture<SearchUserByUsernameComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SearchUserByUsernameComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SearchUserByUsernameComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
