import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Drug} from "../models/drug";

@Component({
  selector: 'app-new-drug',
  templateUrl: './new-drug.component.html',
  styleUrls: ['./new-drug.component.scss']
})
export class NewDrugComponent implements OnInit {

  @ViewChild('drugName') drugName: ElementRef | any;
  @ViewChild('drugPrice') drugPrice: ElementRef | any;
  @ViewChild('drugNumber') drugNumber: ElementRef | any;
  constructor(private http: HttpClient) {
  }

  ngOnInit(): void {
  }

  addDrug(){
    this.http.post(
      'http://localhost:8080/drug',
      {
        drug_name:this.drugName.nativeElement.value,
        amount_in_warehouse:this.drugNumber.nativeElement.value,
        price:this.drugPrice.nativeElement.value
      },
      {responseType: 'text'}
      )
      .subscribe(data =>{
        console.log(data);
      });
  }
}
