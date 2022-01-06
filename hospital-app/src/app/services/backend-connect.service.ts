import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class BackendConnectService {

  private url:string = "http://localhost:8080/";
  private token:string = "";

  constructor() {}

  getEndpoint():string{
    return this.url;
  }


  setToken(token:string){
    this.token = token;
    console.log(this.token)
  }

}
