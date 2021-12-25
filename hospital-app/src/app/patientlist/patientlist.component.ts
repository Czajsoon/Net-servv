import { Component, OnInit } from '@angular/core';
import {Patient} from "../models/patient";
//import {HttpClient} from "@angular/common/http";
import {MatDialog} from "@angular/material/dialog";
import {NewDrugComponent} from "../new-drug/new-drug.component";
import {NewPatientComponent} from "../new-patient/new-patient.component";


let Patients: Patient[]=[];
@Component({
  selector: 'app-patientlist',
  templateUrl: './patientlist.component.html',
  styleUrls: ['./patientlist.component.scss']
})
export class PatientlistComponent implements OnInit {
  displayedColumns: string[] = ['id', 'username', 'firstname', 'surname','identification','bornDate','age','delete'];
  Patient_Table = [...Patients];

  constructor( private dialog: MatDialog) {
  }

  ngOnInit(): void {

  }

  addPatient() {
    this.dialog.open(NewPatientComponent);
  }

  deletePatient(id: number) {
    console.log("deletepatient");
  }
}
