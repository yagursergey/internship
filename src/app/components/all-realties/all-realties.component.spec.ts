import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AllRealtiesComponent } from './all-realties.component';

describe('AllRealtiesComponent', () => {
  let component: AllRealtiesComponent;
  let fixture: ComponentFixture<AllRealtiesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AllRealtiesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AllRealtiesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
