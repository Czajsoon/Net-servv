import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class BackendConnectService {

  private endpoint:string = "http://localhost:8080/";
  private token:string = "";

  constructor() {}

  getEndpoint():string{
    return this.endpoint;
  }

  setToken(token:string){
    this.token = token;
    console.log(this.token)
  }

}
