import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IssuingPrescriptionsComponent } from './issuing-prescriptions.component';

describe('IssuingPrescriptionsComponent', () => {
  let component: IssuingPrescriptionsComponent;
  let fixture: ComponentFixture<IssuingPrescriptionsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ IssuingPrescriptionsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(IssuingPrescriptionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
