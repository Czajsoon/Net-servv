import {Component, Inject, OnInit} from '@angular/core';
import {timetableEvent} from "../models/timetableEvent";
import {MAT_DIALOG_DATA} from "@angular/material/dialog";

@Component({
  selector: 'app-event-timetable',
  templateUrl: './event-timetable.component.html',
  styleUrls: ['./event-timetable.component.scss']
})
export class EventTimetableComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) public event: timetableEvent) { }

  ngOnInit(): void {
  }

}
