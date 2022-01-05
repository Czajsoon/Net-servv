import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialog} from "@angular/material/dialog";
import {timetableEvent} from "../models/timetableEvent";
import {Visit} from "../models/Visit";

@Component({
  selector: 'app-visit-details',
  templateUrl: './visit-details.component.html',
  styleUrls: ['./visit-details.component.scss']
})
export class VisitDetailsComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) public visit: Visit) { }

  ngOnInit(): void {
  }


}
