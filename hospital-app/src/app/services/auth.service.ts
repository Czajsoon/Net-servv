import { Injectable } from '@angular/core';
import {BackendConnectService} from "./backend-connect.service";
import {HttpClient} from "@angular/common/http";
import {User} from "../models/user";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  get token(): { token: string } {
    return this._token;
  }
  get user(): User {
    return this._user;
  }

  set user(value: User) {
    this._user = value;
  }

  // @ts-ignore
  private _token: {token:string};
  // @ts-ignore
  private _user : User = {id:1,name:"John", surname:"Doe"};

  constructor(private bc: BackendConnectService,
              private http: HttpClient) {
  }


  isLoggedIn():boolean{
    // return false;
    return !!this._user;
  }

  login(username:string,password:string){
    return this.http.post("http://localhost:8080/login",{userName:username,password:password}).toPromise().then(data =>{
      // @ts-ignore
      this._token = JSON.parse(JSON.stringify(data));
      this.bc.setToken(this._token.token);
    }).catch(data =>{
      //TODO exception handle!
      console.log(data);
    });
  }
}
