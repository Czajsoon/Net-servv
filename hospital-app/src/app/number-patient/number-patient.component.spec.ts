import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NumberPatientComponent } from './number-patient.component';

describe('NumberPatientComponent', () => {
  let component: NumberPatientComponent;
  let fixture: ComponentFixture<NumberPatientComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NumberPatientComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NumberPatientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
