import { Injectable } from '@angular/core';
import {BackendConnectService} from "./backend-connect.service";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {User} from "../models/user";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  get admin(): boolean {
    return this._admin;
  }

  get userloged(): boolean {
    return this._userloged;
  }

  get doctor(): boolean {
    return this._doctor;
  }

  get recepcionist(): boolean {
    return this._recepcionist;
  }
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
  private _user : User = null;
  // @ts-ignore
  private _admin: boolean;
  // @ts-ignore
  private _userloged: boolean;
  // @ts-ignore
  private _doctor: boolean;
  // @ts-ignore
  private _recepcionist: boolean;

  constructor(private bc: BackendConnectService,
              private http: HttpClient,
              private _router: Router) {
    this._admin = false;
    this._userloged = false;
    this._doctor = false;
    this._recepcionist = false;
  }

  Logut(){
    window.location.reload();
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

      let headers= new HttpHeaders().set("Authorization","Bearer " + this._token.token);
      this.http.get("http://localhost:8080/currentUser", {headers}).toPromise().then(result => {
        console.log(result);
          // @ts-ignore
        this._user = result;
        this._user.roles.forEach(role =>{
          if(role.name == "ADMIN") this._admin = true;
          if(role.name == "USER") this._userloged = true;
          if(role.name == "DOCTOR") this._doctor = true;
          if(role.name == "RECEPTIONIST") this._recepcionist = true;
        })
        this._router.navigateByUrl("/home");
      })
    }).catch(data =>{
      //TODO exception handle!
      console.log(data);
    });
  }
}
