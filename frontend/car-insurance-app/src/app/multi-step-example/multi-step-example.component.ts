import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-multi-step-example',
  templateUrl: './multi-step-example.component.html',
  styleUrls: ['./multi-step-example.component.css']
})
export class MultiStepExampleComponent implements OnInit {
  title = 'newMat';
  isLinear = true;
  firstFormGroup!: FormGroup;
  secondFormGroup!: FormGroup;
  constructor(private _formBuilder: FormBuilder) {}
  ngOnInit() {
    this.firstFormGroup = this._formBuilder.group({
      name: ['', Validators.required],
      description: ['', Validators.required]
    });
    this.secondFormGroup = this._formBuilder.group({
      amount: ['', Validators.required],
      stock: ['', Validators.required]
    });
  }
  submit(){
    console.log(this.firstFormGroup.value);
    console.log(this.secondFormGroup.value);
  }
}

