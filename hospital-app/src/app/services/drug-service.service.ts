import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {BackendConnectService} from "./backend-connect.service";
import {map} from "rxjs/operators";
import {Drug} from "../models/drug";

@Injectable({
  providedIn: 'root'
})
export class DrugServiceService {

  private readonly configUrl:string;

  constructor(
    private backend:BackendConnectService,
    private http:HttpClient) {
    this.configUrl = backend.getEndpoint() + "drug";
  }

  getDrugs():Promise<any>{
    return this.http.get(this.configUrl).toPromise();
  }

  postDrug(drug_name:string,amount_in_warehouse:string,price:string):Promise<any>{
    return this.http.post(this.configUrl,
      {
        drug_name:drug_name,
        amount_in_warehouse:amount_in_warehouse,
        price:price
    }).toPromise();
  }

  deleteDrug(id:number):Promise<any>{
    return this.http.delete(this.configUrl + "/" + id).toPromise();
  }
}
