import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Users.AllComponent } from './users.all.component';

describe('Users.AllComponent', () => {
  let component: Users.AllComponent;
  let fixture: ComponentFixture<Users.AllComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Users.AllComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Users.AllComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
