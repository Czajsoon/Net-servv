import { Injectable } from '@angular/core';
import {BackendConnectService} from "./backend-connect.service";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class AdminPanelService {

  constructor(private backend: BackendConnectService,
              private http:HttpClient) {
  }

  getRoles(){
    return this.http.get("http://localhost:8080/api/roles").toPromise();
  }

  getUsersWithRole(id:number){
    return this.http.get("http://localhost:8080/api/users/" + id).toPromise();
  }
}
