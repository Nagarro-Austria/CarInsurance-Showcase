import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MultiStepExampleComponent } from './multi-step-example.component';

describe('MultiStepExampleComponent', () => {
  let component: MultiStepExampleComponent;
  let fixture: ComponentFixture<MultiStepExampleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MultiStepExampleComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MultiStepExampleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
