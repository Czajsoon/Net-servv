import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {timetableEvent} from "../models/timetableEvent";
import {MatDialog} from "@angular/material/dialog";
import {EventTimetableComponent} from "../event-timetable/event-timetable.component";

@Component({
  selector: 'app-nurse-timetable',
  templateUrl: './nurse-timetable.component.html',
  styleUrls: ['./nurse-timetable.component.scss']
})
export class NurseTimetableComponent implements OnInit {
  range = new FormGroup({
    start: new FormControl(),
    end: new FormControl(),
  });
  date: Date = new Date(2021,2,20);
  date1: Date = new Date(2021,3,30);
  date2: Date = new Date(2021,1,30);
  date3: Date = new Date(2021,4,3);
  date4: Date = new Date(2021,5,2);
  date5: Date = new Date(2022,11,12);
  date6: Date = new Date(2021,10,30);
  events = [{type:"Badanie",date:this.date,dateEnd:null,doctor:"Jan kowalski",spec:"Kardiolog",description:"Badanie krwi"},
    {type:"Wizyta",date:this.date1,dateEnd:null,doctor:"Jan kowalski",spec:"Kardiolog",description:"wizyta w sprawie przeziębienia"},
    {type:"Pobyt",date:this.date2,dateEnd:this.date3,doctor:"Jan kowalski",spec:"Kardiolog",description:"pobyt w szpitalu po potrąceniu przez samochód osobowy"},
    {type:"Pobyt",date:this.date4,dateEnd:this.date6,doctor:"Jan kowalski",spec:"Kardiolog",description:"pobyt w szpitalu po potrąceniu przez samochód osobowy"},
    {type:"Badanie",date:this.date5,dateEnd:null,doctor:"Jan kowalski",spec:"Kardiolog",description:"Badanie krwi"},
    {type:"Wizyta",date:this.date6,dateEnd:null,doctor:"Jan kowalski",spec:"Kardiolog",description:"wizyta w sprawie przeziębienia"},
    {type:"Wizyta",date:this.date4,dateEnd:null,doctor:"Jan kowalski",spec:"Kardiolog",description:"wizyta w sprawie przeziębienia"}];

  test = false;
  visit = false;
  stay = false;
  operation = false;
  eventOrigin;

  constructor(private dialog:MatDialog) {
    this.events.sort((x,y)=>{
      return y.date.getTime() - x.date.getTime();
    })
    this.eventOrigin = this.events
  }

  ngOnInit(): void {

  }

  showDetails(event:timetableEvent){
    this.dialog.open(EventTimetableComponent,{
      width: '40%',
      data: event
    });
  }

  clearEvents(){
    this.test = false;
    this.visit = false;
    this.stay = false;
    this.doFilter("");
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
          if(this.range.get('start')?.value <= event.date){
            if(event.dateEnd) return this.range.get('end')?.value >= event.dateEnd;
            else return this.range.get('end')?.value >= event.date;
          }
          else return false;
        })
      }
      else{
        if(this.range.get('start')?.value){
          this.eventOrigin = this.events.slice().filter(event =>{
            if(event.dateEnd) return this.range.get('start')?.value <= event.date && this.range.get('start')?.value <= event.dateEnd
            else return this.range.get('start')?.value <= event.date
          })
        }
        else this.eventOrigin = this.events.slice().filter(event => this.range.get('end')?.value >= event.date)
      }
    }
    else this.eventOrigin = this.events;

    switch (event){
      case "test": this.test = !this.test; break;
      case "visit": this.visit = !this.visit; break;
      case "stay": this.stay = !this.stay; break;
      case "operation": this.stay = !this.stay; break;
      default: break;
    }
    if(event){
      if(this.test && this.visit && this.stay)
        this.eventOrigin = this.eventOrigin.slice().filter(event => event.type=="Wizyta" || event.type=="Badanie" || event.type=="Pobyt" || event.type=="operation");
      else if(this.test && this.visit)
        this.eventOrigin = this.eventOrigin.slice().filter(event => event.type=="Wizyta" || event.type=="Badanie");
      else if(this.test && this.stay)
        this.eventOrigin = this.eventOrigin.slice().filter(event => event.type=="Pobyt" || event.type=="Badanie");
      else if(this.visit && this.stay)
        this.eventOrigin = this.eventOrigin.slice().filter(event => event.type=="Pobyt" || event.type=="Wizyta");
      else if(this.test)
        this.eventOrigin = this.eventOrigin.filter(event => event.type=="Badanie")
      else if(this.visit)
        this.eventOrigin = this.eventOrigin.filter(event => event.type=="Wizyta")
      else if(this.stay)
        this.eventOrigin = this.eventOrigin.filter(event => event.type=="Pobyt");
      else {
        if(!this.range.get('start')?.value || !this.range.get('end')?.value) this.eventOrigin = this.events;
      }
    }
  }


}
