import { Component, OnInit } from '@angular/core';

import {MatDialog} from "@angular/material/dialog";
import {NewPatientComponent} from "../new-patient/new-patient.component";
import {PatientlistService} from "../services/patientlist.service";
import {Patient} from "../models/Patient";


let Patients: Patient[]=[];
@Component({
  selector: 'app-patientlist',
  templateUrl: './patientlist.component.html',
  styleUrls: ['./patientlist.component.scss']
})
export class PatientlistComponent implements OnInit {
  displayedColumns: string[] = ['id', 'name', 'surname','sex', 'identification','bornDate','delete'];
  Patient_Table = [...Patients];

  constructor(private dialog: MatDialog,private patientService: PatientlistService) {
  }

  ngOnInit(): void {
    this.patientService.getPatients().then(data=>{this.updateTablePatients(data);});
  }

  updateTablePatients(response:any){
    this.Patient_Table=[];
    for(let i=0;i<response.length;i++){
      this.Patient_Table= [...this.Patient_Table,
        {
          id:response[i].id,
          name:response[i].name,
          surname:response[i].surname,
          sex:response[i].sex,
          identification:response[i].identification,
          bornDate:response[i].bornDate
        }]
    }
  }

  addPatient() {
    this.dialog.open(NewPatientComponent);
  }

  deletePatient(id: number) {
    this.patientService.deletePatient(id)
      .then(data=>{
        console.log(data);
      this.updateTablePatients(data)})
  }
}
