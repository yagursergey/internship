import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Realties.Deleted.ListComponent } from './realties.deleted.list.component';

describe('Realties.Deleted.ListComponent', () => {
  let component: Realties.Deleted.ListComponent;
  let fixture: ComponentFixture<Realties.Deleted.ListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Realties.Deleted.ListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Realties.Deleted.ListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
