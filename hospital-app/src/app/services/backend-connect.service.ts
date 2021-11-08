import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class BackendConnectService {

  private endpoint:string = "http://localhost:8080/";

  constructor() {}

  getEndpoint():string{
    return this.endpoint;
  }

}
