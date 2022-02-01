import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialog} from "@angular/material/dialog";
import {timetableEvent} from "../models/timetableEvent";
import {Visit} from "../models/Visit";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ReceptionistService} from "../services/receptionist.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {visit} from "@angular/compiler-cli/src/ngtsc/util/src/visitor";

@Component({
  selector: 'app-visit-details',
  templateUrl: './visit-details.component.html',
  styleUrls: ['./visit-details.component.scss']
})
export class VisitDetailsComponent implements OnInit {
  editMode: Boolean = false;
  // @ts-ignore
  editDate: FormGroup;
  visitHour:string[] = ["06","07","08","09","10","11","12","13"];
  visitMinutes:string[] = ["00","20","40"];

  constructor(@Inject(MAT_DIALOG_DATA) public visit: Visit,
              private _formBuilder: FormBuilder,
              private reception: ReceptionistService,
              private _snack: MatSnackBar) { }

  ngOnInit(): void {
    this.editDate = this._formBuilder.group({
      date: ['',Validators.required],
      hour: ['',Validators.required],
      minutes: ['',Validators.required]
    })
  }

  changeVisitDate(){
    let date = new Date(this.editDate.controls['date'].value);
    date.setDate(date.getDate() + 1);
    let finalDate = date.toISOString().substr(0,date.toISOString().length-13)
      + this.editDate.controls['hour'].value + ":"
      + this.editDate.controls['minutes'].value + ":00";
    let editVisit = {id: this.visit.id,date: finalDate};
    this.reception.editVisit(editVisit).then(result =>{
      // @ts-ignore
      this._snack.open(result,'',{
        duration: 5000,
      })
    }).catch(reason => {
      this._snack.open(reason.error.text,'',{
        duration: 5000,
      })
    });
  }

  cancelVisit(){
    this.reception.cancelVisit(this.visit.id).then(response =>{
      // @ts-ignore
      this._snack.open(response,'',{
        duration:5000,
        panelClass: ['success']
      })
    }).catch(reason => {
      this._snack.open(reason.error.text,'',{
        duration:5000,
        panelClass: ['success']
      })
    })
  }
}
