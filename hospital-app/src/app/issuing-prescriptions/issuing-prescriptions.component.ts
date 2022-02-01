import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-issuing-prescriptions',
  templateUrl: './issuing-prescriptions.component.html',
  styleUrls: ['./issuing-prescriptions.component.scss']
})
export class IssuingPrescriptionsComponent implements OnInit {
  // @ts-ignore
  descInput1: string
  // @ts-ignore
  descInput2: string
  // @ts-ignore
  descInput3: string
  // @ts-ignore
  descInput4: string
  // @ts-ignore
  descInput5: string
  // @ts-ignore
  descInput6: string
  // @ts-ignore
  descInput7: string
  // @ts-ignore
  descInput8: string

  constructor() { }

  ngOnInit(): void {
  }
  gets(){

    console.log(this.descInput1);
    console.log(this.descInput2);
    console.log(this.descInput3);
    console.log(this.descInput4);
    console.log(this.descInput5);
    console.log(this.descInput6);
    console.log(this.descInput7);
    console.log(this.descInput8);
  }
}

