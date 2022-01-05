import {Component, Inject, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {Visit} from "../models/Visit";
import {Specialization} from "../models/specialization";

@Component({
  selector: 'app-add-visit',
  templateUrl: './add-visit.component.html',
  styleUrls: ['./add-visit.component.scss']
})
export class AddVisitComponent implements OnInit {
  // @ts-ignore
  firstFormGroup:FormGroup
  // @ts-ignore
  secoundFormGroup:FormGroup
  specializations: Specialization[] = []

  constructor(@Inject(MAT_DIALOG_DATA) public spec: Specialization,
              private _formBuilder: FormBuilder) {
    // @ts-ignore
    this.specializations = spec;
  }

  ngOnInit(): void {
    this.firstFormGroup = this._formBuilder.group({
      firstCtrl: ['', Validators.required],
    });
    this.secoundFormGroup = this._formBuilder.group({
      firstCtrl: ['', Validators.required],
    });
  }

}
