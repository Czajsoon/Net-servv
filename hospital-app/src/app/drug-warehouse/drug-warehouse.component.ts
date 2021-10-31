import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Drug} from "../models/drug";
import {MatDialog} from "@angular/material/dialog";
import {NewDrugComponent} from "../new-drug/new-drug.component";

let Drugs:Drug[] = [

];

@Component({
  selector: 'app-drug-warehouse',
  templateUrl: './drug-warehouse.component.html',
  styleUrls: ['./drug-warehouse.component.scss']
})
export class DrugWarehouseComponent implements OnInit {
  displayedColumns: string[] = ['id', 'drug_name', 'amount_in_warehouse', 'price','delete'];
  Drug_Table = [...Drugs];
  response:any;

  constructor(private http: HttpClient,
              private dialog: MatDialog) {

  }

  ngOnInit(): void {
    this.http.get('http://localhost:8080/drug').subscribe(data =>{
      this.updateTableDrugs(data)
    });
  }

  updateTableDrugs(response:any){
    this.Drug_Table = [];
    for(let i=0;i<response.length;i++){
      this.Drug_Table = [...this.Drug_Table,
        {
          id:response[i].id,
          drug_name:response[i].drug_name,
          amount_in_warehouse:response[i].amount_in_warehouse,
          price:response[i].price
        }]
    }
  }

  deleteDrug(id:number){
    let url = 'http://localhost:8080/drug/'+id;
    this.http.delete(url).subscribe(data=>{
      this.updateTableDrugs(data);
    })
  }

  addFile(){
    this.dialog.open(NewDrugComponent);
  }


}
