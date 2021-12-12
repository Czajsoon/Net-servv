import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {timetableEvent} from "../models/timetableEvent";
import {MatDialog} from "@angular/material/dialog";
import {EventTimetableComponent} from "../event-timetable/event-timetable.component";
import {BackendConnectService} from "../services/backend-connect.service";
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-nurse-timetable',
  templateUrl: './nurse-timetable.component.html',
  styleUrls: ['./nurse-timetable.component.scss']
})
export class NurseTimetableComponent implements OnInit{
  range = new FormGroup({
    start: new FormControl(),
    end: new FormControl(),
  });

  events:timetableEvent[] = [] ;
  visit = false;
  stay = false;
  eventOrigin:timetableEvent[] = [];

  constructor(private dialog:MatDialog,
              private backend:BackendConnectService,
              private http:HttpClient,
              private act:ActivatedRoute) {
    act.data.subscribe(result =>{
      this.sortDates(result.time_table);
      this.events = [...result.time_table]
      console.log(result.time_table);
      this.eventOrigin = this.events
    })
  }

   ngOnInit():void {

   }

   sortDates(events:timetableEvent[]){
     events.sort((x:timetableEvent,y:timetableEvent) =>{
       let date = new Date(y.activity.startDate);
       let date1 = new Date(x.activity.startDate);
       return date1.getDate() - date.getDate();
     })
     console.log(events);
  }

  showDetails(event:timetableEvent){
    this.dialog.open(EventTimetableComponent,{
      width: '40%',
      data: event
    });
  }

  clearEvents(){
    this.visit = false;
    this.stay = false;
    this.doFilter("1");
  }

  clearDates(){
    this.range.get('start')?.setValue(null);
    this.range.get('end')?.setValue(null);
    this.doFilter('event');
  }

  doFilter(event:string){
    if(this.range.get('start')?.value || this.range.get('end')?.value){
      if(this.range.get('start')?.value && this.range.get('end')?.value){
        this.eventOrigin = this.events.slice().filter(event => {
          let startDate = new Date(event.activity.startDate);
          if(this.range.get('start')?.value <= startDate){
            if(event.activity.endDate){
              let date = new Date(event.activity.endDate)
              return this.range.get('end')?.value >= date;
            }
            else return this.range.get('end')?.value >= startDate;
          }
          else return false;
        })
      }
      else{
        if(this.range.get('start')?.value){
          this.eventOrigin = this.events.slice().filter(event =>{
            let startDate = new Date(event.activity.startDate);
            if(event.activity.endDate) {
              let date = new Date(event.activity.endDate);
              return this.range.get('start')?.value <= startDate && this.range.get('start')?.value <= date
            }
            else return this.range.get('start')?.value <= startDate
          })
        }
        else this.eventOrigin = this.events.slice().filter(event => this.range.get('end')?.value >= new Date(event.activity.startDate))
      }
    }
    else this.eventOrigin = this.events;
    switch (event){
      case "visit": this.visit = !this.visit; break;
      case "stay": this.stay = !this.stay; break;
      default: break;
    }
    if(event){
      if((!this.visit && !this.stay) || (this.visit && this.stay))
        this.eventOrigin = this.events;
      else if(this.visit)
        this.eventOrigin = this.eventOrigin.slice().filter(event => event.type == "Wizyta");
      else if (this.stay)
        this.eventOrigin = this.eventOrigin.slice().filter(event => event.type=="Pobyt");
      else {
        if(!this.range.get('start')?.value || !this.range.get('end')?.value) this.eventOrigin = this.events;
      }
    }
  }
}
