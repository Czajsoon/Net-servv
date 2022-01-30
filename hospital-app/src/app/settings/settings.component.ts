import { Component, OnInit } from '@angular/core';
import {BackendConnectService} from "../services/backend-connect.service";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {AuthService} from "../services/auth.service";


@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.scss']
})
export class SettingsComponent implements OnInit {

  get results(): any {
    return this._results;
  }



  private readonly configUrl:string;


  constructor(private backend:BackendConnectService,
              private http:HttpClient,
              private auth:AuthService) {
    this.configUrl = backend.getEndpoint() + "currentUser";

  }

  private _results:any;


  ngOnInit(): void {

    let token=this.auth.token;
    console.log(token.token);
    this.getCurrent().then(result=>{
      console.log(result);
      this._results=result;
    })
  }




  getCurrent():Promise<any>{
    let token=this.auth.token;
    let headers= new HttpHeaders().set("Authorization","Bearer "+token.token);
    return this.http.get(this.configUrl,{headers},).toPromise();
  }
}
