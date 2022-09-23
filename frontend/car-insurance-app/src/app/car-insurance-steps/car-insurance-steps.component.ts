import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-car-insurance-steps',
  templateUrl: './car-insurance-steps.component.html',
  styleUrls: ['./car-insurance-steps.component.css']
})
export class CarInsuranceStepsComponent implements OnInit {

  isLinear = true;
  carDetailsFormGroup!: FormGroup;
  contractDetailsFormGroup!: FormGroup;
  personDetailsFormGroup!: FormGroup;

  fuelTypeTable = [
    {value: 'benzin', viewValue: 'Benzin'},
    {value: 'diesel', viewValue: 'Diesel'},
    {value: 'hybrid', viewValue: 'Hybrid'},
    {value: 'electro', viewValue: 'Elektro'}
  ];

  bonusMalusTable = [
    {value: '0', viewValue: 'Bonus-0'},
    {value: '1', viewValue: 'Bonus-1'},
    {value: '2', viewValue: 'Bonus-2'},
    {value: '3', viewValue: 'Bonus-3'},
    {value: '4', viewValue: 'Bonus-4'},
    {value: '5', viewValue: 'Bonus-5'},
    {value: '6', viewValue: 'Bonus-6'},
    {value: '7', viewValue: 'Bonus-7'},
    {value: '8', viewValue: 'Bonus-8'},
    {value: '9', viewValue: '9'},
    {value: '10', viewValue: 'Malus-10'},
    {value: '11', viewValue: 'Malus-11'},
    {value: '12', viewValue: 'Malus-12'},
    {value: '13', viewValue: 'Malus-13'},
    {value: '14', viewValue: 'Malus-14'},
    {value: '15', viewValue: 'Malus-15'},
    {value: '16', viewValue: 'Malus-16'},
    {value: '17', viewValue: 'Malus-17'}
  ];

  constructor(private _formBuilder: FormBuilder) {
  }

  ngOnInit() {
    this.carDetailsFormGroup = this._formBuilder.group({
      dateOfManufacture: ['', Validators.required],
      fuelType: ['', Validators.required],
      performance: ['', Validators.required],
      enginePerformance: ['', Validators.required],
    });
    this.contractDetailsFormGroup = this._formBuilder.group({
      dateOfStart: ['', Validators.required],
      coverage: ['', Validators.required],
      level: ['', Validators.required]
    });
    this.personDetailsFormGroup = this._formBuilder.group({
      name: ['', Validators.required],
      dayOfBirth: ['', Validators.required],
      zipCode: ['', Validators.required]
    });
  }

  submit() {
    console.log(this.carDetailsFormGroup.value);
    console.log(this.contractDetailsFormGroup.value);
    console.log(this.personDetailsFormGroup.value);
  }

}
