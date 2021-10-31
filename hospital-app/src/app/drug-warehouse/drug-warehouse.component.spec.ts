import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DrugWarehouseComponent } from './drug-warehouse.component';

describe('DrugWarehouseComponent', () => {
  let component: DrugWarehouseComponent;
  let fixture: ComponentFixture<DrugWarehouseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DrugWarehouseComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DrugWarehouseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
