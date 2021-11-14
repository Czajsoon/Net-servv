import { Injectable } from '@angular/core';
import {BackendConnectService} from "./backend-connect.service";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  // @ts-ignore
  private token: {token:string};

  constructor(private bc: BackendConnectService,
              private http: HttpClient) {
  }

  login(username:string,password:string){
    return this.http.post("http://localhost:8080/login",{userName:username,password:password}).toPromise().then(data =>{
      // @ts-ignore
      this.token = JSON.parse(JSON.stringify(data));
      this.bc.setToken(this.token.token);
    }).catch(data =>{
      //TODO exception handle!
      console.log(data);
    });
  }
}
