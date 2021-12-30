import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {PatientlistService} from "../services/patientlist.service";

@Component({
  selector: 'app-new-patient',
  templateUrl: './new-patient.component.html',
  styleUrls: ['./new-patient.component.scss']
})

export class NewPatientComponent implements OnInit {

  @ViewChild('patientUserName') patientUserName: ElementRef | any;
  @ViewChild('patientFirstname') patientFirstname: ElementRef | any;
  @ViewChild('patientSurname') patientSurname: ElementRef | any;
  @ViewChild('patientIdentification') patientIdentification: ElementRef | any;
  @ViewChild('patientBornDate') patientBornDate: ElementRef | any;
  @ViewChild('patientPassword') patientPassword: ElementRef | any;
  constructor(
    private patientService: PatientlistService) {
  }

  ngOnInit(): void {
  }

  addPatient(){
    this.patientService.postPatient(
                                    this.patientPassword.nativeElement.value,
                                    this.patientUserName.nativeElement.value,
                                    this.patientFirstname.nativeElement.value,
                                    this.patientSurname.nativeElement.value,
                                    this.patientIdentification.nativeElement.value,
                                    this.patientBornDate.nativeElement.value,
      ).then(r=>console.log(r));
  }
}
