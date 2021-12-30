import { Component, OnInit } from '@angular/core';
import {Patient} from "../models/patient";
import {MatDialog} from "@angular/material/dialog";
import {NewPatientComponent} from "../new-patient/new-patient.component";
import {HttpClient} from "@angular/common/http";
import {PatientlistService} from "../services/patientlist.service";


let Patients: Patient[]=[];
@Component({
  selector: 'app-patientlist',
  templateUrl: './patientlist.component.html',
  styleUrls: ['./patientlist.component.scss']
})
export class PatientlistComponent implements OnInit {
  displayedColumns: string[] = ['id', 'username', 'firstname', 'surname','identification','bornDate','delete'];
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
          username:response[i].username,
          firstname:response[i].name,
          surname:response[i].surname,
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
