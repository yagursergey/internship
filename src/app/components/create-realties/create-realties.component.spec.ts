import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateRealtiesComponent } from './create-realties.component';

describe('CreateRealtiesComponent', () => {
  let component: CreateRealtiesComponent;
  let fixture: ComponentFixture<CreateRealtiesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateRealtiesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateRealtiesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
