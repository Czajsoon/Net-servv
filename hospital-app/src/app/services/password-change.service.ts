import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {BackendConnectService} from "./backend-connect.service";
import {AuthService} from "./auth.service";
import {SettingsComponent} from "../settings/settings.component";

@Injectable({
  providedIn: 'root'
})
export class PasswordChangeService {

  private readonly configUrl:string;
  private readonly configUrl1:string;
  results: any;
  id1:any;

  constructor(private backend:BackendConnectService,
              private http:HttpClient,
              private auth:AuthService,


              ) {
    this.configUrl = backend.getEndpoint() + "currentUser";
    this.configUrl1= backend.getEndpoint()+ "api/change"

  }

  getId():any{
    this.getCurrent().then(result=>{
      this.results=result;

      return result.id;
    })
  }
  getCurrent():Promise<any>{
    let token=this.auth.token;
    let headers= new HttpHeaders().set("Authorization","Bearer "+token.token);
    return this.http.get(this.configUrl,{headers},).toPromise();
  }

  postChange(id:string,oldPassword:string,newPassword:string):Promise<any>{
    let id1= this.auth.user.id;
    console.log("xd"+id1);

    return this.http.post(this.configUrl1,
      {
        id1,
        oldPassword:oldPassword,
        newPassword:newPassword
      }).toPromise();
  }
}
