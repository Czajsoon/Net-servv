import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MatStepperIntl} from "@angular/material/stepper";


@Component({
  selector: 'app-add-test-results',
  templateUrl: './add-test-results.component.html',
  styleUrls: ['./add-test-results.component.scss']
})
export class AddTestResultsComponent implements OnInit {
  // @ts-ignore
  firstFormGroup: FormGroup;
  // @ts-ignore
  secondFormGroup: FormGroup;
  // @ts-ignore
  optionalLabelText: string;
  optionalLabelTextChoices: string[] = ['Morfologia krwii', 'CRP', 'APTT', 'INR'];

  constructor(private _formBuilder: FormBuilder, private _matStepperIntl: MatStepperIntl) {}

  updateOptionalLabel() {
    this._matStepperIntl.optionalLabel = this.optionalLabelText;
    // Required for the optional label text to be updated
    // Notifies the MatStepperIntl service that a change has been made
    this._matStepperIntl.changes.next();
  }

  ngOnInit() {
    this.firstFormGroup = this._formBuilder.group({
      firstCtrl: ['', Validators.required],
    });
    this.secondFormGroup = this._formBuilder.group({
      secondCtrl: ['', Validators.required],
    });
  }
}
