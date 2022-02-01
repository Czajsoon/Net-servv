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

  addUser(user:any){
    return this.http.post(this.backend.getEndpoint() + "register",user).toPromise();
  }

  addDoctor(doctor:any){
    return this.http.post(this.backend.getEndpoint() + "api/doctor",doctor).toPromise();
  }

  editUser(user:any,id:number){
    return this.http.put("http://localhost:8080/api/users/edit/" + id,user).toPromise();
  }

  forcePassword(password:string,id:number){
    return this.http.put(this.backend.getEndpoint() + "api/users/forceChangePassword/" + id + "?newPassword=" + password,null).toPromise();
  }

  deleteUser(id:number){
    return this.http.delete(this.backend.getEndpoint() + "api/users/" + id).toPromise();
  }
}
