import { Component, OnInit } from '@angular/core';
import {Refferal} from "../models/refferal";
import{RefferalServiceService} from "../services/refferal-service.service";

let Refferals: Refferal[]=[];
@Component({
  selector: 'app-referralslist',
  templateUrl: './referralslist.component.html',
  styleUrls: ['./referralslist.component.scss']
})
export class ReferralslistComponent implements OnInit {

  displayedColumns: string[] = ['id', 'startDate', 'endDate', 'description','user','doctor','visit','delete'];
  Refferral_Table = [...Refferals];

  constructor(private refferalService:RefferalServiceService ) { }

  ngOnInit(): void {
    this.refferalService.getRefferals().then(data=>{this.updateTableRefferals(data);});
  }

  updateTableRefferals(response:any){
    this.Refferral_Table=[];
    for(let i=0;i<response.length;i++){
      this.Refferral_Table= [...this.Refferral_Table,
        {
          id:response[i].id,
          startDate:response[i].startDate,
          endDate:response[i].endDate,
          description:response[i].description,
          user:response[i].user,
          doctor:response[i].doctor,
          visit:response[i].visit
        }]
    }
  }


  deleteRefferal1(id:number){
    this.refferalService.deleteRefferal(id)
      .then(data=>{
        console.log(data);
        this.updateTableRefferals(data)})
  }
}
