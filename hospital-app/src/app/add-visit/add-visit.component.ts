import {Component, ElementRef, Inject, OnInit, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {Specialization} from "../models/specialization";
import {ViewDoctorsServiceService} from "../services/view-doctors-service.service";
import {Doctor} from "../models/Doctor";
import {VisitType} from "../models/VisitType";
import {ReceptionistService} from "../services/receptionist.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {VisitReq} from "../models/VisitReq";

@Component({
  selector: 'app-add-visit',
  templateUrl: './add-visit.component.html',
  styleUrls: ['./add-visit.component.scss']
})
export class AddVisitComponent implements OnInit {
  doctors: Doctor[];
  visitTypes:VisitType[];
  visitHour:string[] = ["06","07","08","09","10","11","12","13"];
  visitMinutes:string[] = ["00","20","40"];
  visitOption;
  // @ts-ignore
  @ViewChild('datePick') date:ElementRef;
  // @ts-ignore
  @ViewChild('userIdentification') id:ElementRef;
  // @ts-ignore
  @ViewChild('timePick') time:ElementRef;
  specOption;
  docOption;
  // @ts-ignore
  firstFormGroup: FormGroup;
  // @ts-ignore
  secondFormGroup: FormGroup;
  specializations: Specialization[] = []

  constructor(@Inject(MAT_DIALOG_DATA) public spec: Specialization,
              private _formBuilder: FormBuilder,
              private doctorsService: ViewDoctorsServiceService,
              private reception:ReceptionistService,
              private _snackBar: MatSnackBar) {
    this.doctors = [];
    this.visitTypes = [];
    doctorsService.getVisitTypes().then(result =>{
      // @ts-ignore
      this.visitTypes = result;
    })
    this.visitOption = "";
    this.docOption = "";
    this.specOption = "";
    // @ts-ignore
    this.specializations = spec;
  }

  ngOnInit(): void {
    this.firstFormGroup = this._formBuilder.group({
      specialization: ['', Validators.required]
    });
    this.secondFormGroup = this._formBuilder.group({
      doctor : ['',Validators.required],
      visitDate: ['',Validators.required],
      hour: ['',Validators.required],
      minutes: ['',Validators.required],
      visitType: ['',Validators.required],
      identification: ['',Validators.required]
    });
  }

  getDoctors(spec:string){
    this.doctorsService.getDoctors(spec).then(result =>{
      // @ts-ignore
      this.doctors = result;
    })
  }

  addVisit(){
    let dateN = new Date(this.date.nativeElement.value);
    dateN.setDate(dateN.getDate() + 1);
    let dateString = dateN.toISOString().substr(0,dateN.toISOString().length-13)
      + this.secondFormGroup.controls['hour'].value
      + ":" + this.secondFormGroup.controls['minutes'].value + ":00";
    let doctor = this.secondFormGroup.controls['doctor'].value;
    let visitType = this.secondFormGroup.controls['visitType'].value;
    let identification = this.secondFormGroup.controls['identification'].value;
    let visit:VisitReq = new class implements VisitReq {
      date: string = dateString;
      description: string = "";
      doctor: number = doctor;
      user: number = identification;
      visitType: number = visitType;
    }
    this.reception.addVisit(visit).then(result =>{
      this._snackBar.open("Wizyta dodana pomyślnie!","",{
        duration: 5000,
        panelClass: ['success']
      });
    }).catch(reason => {
      this._snackBar.open(reason.error.text,"",{
        duration: 5000,
        panelClass: ['failure']
      });
    })
  }
}

