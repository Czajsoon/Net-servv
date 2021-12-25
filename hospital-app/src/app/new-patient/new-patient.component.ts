import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {DrugServiceService} from "../services/drug-service.service";

@Component({
  selector: 'app-new-patient',
  templateUrl: './new-patient.component.html',
  styleUrls: ['./new-patient.component.scss']
})

export class NewPatientComponent implements OnInit {

  @ViewChild('patientName') patientUserName: ElementRef | any;
  @ViewChild('patientFirstname') patientFirstname: ElementRef | any;
  @ViewChild('patientSurname') patientSurname: ElementRef | any;
  @ViewChild('patientIdentification') patientIdentification: ElementRef | any;
  @ViewChild('patientBornDate') patientBornDate: ElementRef | any;
  @ViewChild('patientAge') patientAge: ElementRef | any;
  constructor(
    private drugService: DrugServiceService) {
  }

  ngOnInit(): void {
  }

  addPatient(){
    console.log("addpatient");
  }
}
