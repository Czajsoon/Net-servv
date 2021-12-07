import { Component, OnInit } from '@angular/core';
import {Patient} from "../models/patient";

@Component({
  selector: 'app-patientlist',
  templateUrl: './patientlist.component.html',
  styleUrls: ['./patientlist.component.scss']
})
export class PatientlistComponent implements OnInit {
  displayedColumns: string[] = ['id', 'Name', 'Surname', 'Wiek','delete'];
  Patient_Table = [];

  constructor() {
  }

  ngOnInit(): void {
  }

  addFile() {
    console.log("addfile")
  }

  deletePatient(id: number) {
    console.log("deletepatient");
  }
}
