import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { User.OverviewComponent } from './user.overview.component';

describe('User.OverviewComponent', () => {
  let component: User.OverviewComponent;
  let fixture: ComponentFixture<User.OverviewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ User.OverviewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(User.OverviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
