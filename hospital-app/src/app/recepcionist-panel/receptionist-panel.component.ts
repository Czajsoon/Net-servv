import {Component, OnInit} from '@angular/core';
import {ReceptionistService} from "../services/receptionist.service";
import {Specialization} from "../models/specialization";
import {FormControl, FormGroup} from "@angular/forms";
import {Visit} from "../models/Visit";

let specsTable:Specialization[] = [];
let visitsTable:Visit[] = [];

@Component({
  selector: 'app-recepcionist-panel',
  templateUrl: './receptionist-panel.component.html',
  styleUrls: ['./receptionist-panel.component.scss']
})
export class ReceptionistPanelComponent implements OnInit {
  specOption ="";
  surnamePatient = "";
  surnameDoctor ="";
  date = new Date();
  range = new FormGroup({
    start: new FormControl(),
    end: new FormControl(),
  });
  public specializations = [...specsTable];
  public visits = [...visitsTable];

  constructor(private reception:ReceptionistService) {
    reception.getSpecialisations()
      .then(result =>{
      // @ts-ignore
      this.specializations = result;
    })
    reception.getVisits(this.specOption
      ,null
      ,null
      ,""
      ,"")
      .then(visits =>{
      // @ts-ignore
      this.visits = visits
    })
  }

  ngOnInit(): void {
  }

  clearFilters(){
    this.specOption = "";
    this.range.get('start')?.setValue(null);
    this.range.get('end')?.setValue(null);
    this.surnamePatient = "";
    this.surnameDoctor = "";
  }

  search(){
    this.reception
      .getVisits(this.specOption
        ,this.range.get('start')?.value
        ,this.range.get('end')?.value
        ,this.surnameDoctor
        ,this.surnamePatient)
      .then(visits =>{
      // @ts-ignore
      this.visits = visits
    })
  }

}
