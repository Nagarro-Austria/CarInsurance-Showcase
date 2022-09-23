import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CarInsuranceStepsComponent } from './car-insurance-steps.component';

describe('CarInsuranceStepsComponent', () => {
  let component: CarInsuranceStepsComponent;
  let fixture: ComponentFixture<CarInsuranceStepsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CarInsuranceStepsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CarInsuranceStepsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
