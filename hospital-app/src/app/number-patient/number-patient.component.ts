import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-number-patient',
  templateUrl: './number-patient.component.html',
  styleUrls: ['./number-patient.component.scss']
})
export class NumberPatientComponent implements OnInit {
// @ts-ignore
  descInput: string
  // @ts-ignore
  descInput2: string
  // @ts-ignore
  descInput3: string
  // @ts-ignore
  descInput4: string

  constructor() { }

  ngOnInit(): void {
  }
getUserInput(){

  console.log(this.descInput);
  console.log(this.descInput2);
  console.log(this.descInput3);
  console.log(this.descInput4);
}
}
