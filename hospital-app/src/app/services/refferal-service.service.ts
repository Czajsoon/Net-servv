import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {BackendConnectService} from "./backend-connect.service";
import {map} from "rxjs/operators";


@Injectable({
  providedIn: 'root'
})
export class RefferalServiceService {

  private readonly configUrl:string;
  private readonly configUrlDoc:string;
  private readonly configUrlVis:string;
  private readonly configUrlUser:string;

  constructor(
    private backend:BackendConnectService,
    private http:HttpClient) {
    this.configUrl = backend.getEndpoint() + "api/refferalAbsention";
    this.configUrlDoc = backend.getEndpoint() + "api/doctor";
    this.configUrlVis = backend.getEndpoint() + "api/visits";
    this.configUrlUser = backend.getEndpoint() + "api/users";
  }

  getDoctors():Promise<any>{
    return this.http.get(this.configUrlDoc).toPromise();
  }
  getVisits():Promise<any>{
    return this.http.get(this.configUrlVis).toPromise();
  }
  getUsers():Promise<any>{
    return this.http.get(this.configUrlUser).toPromise();
  }

  getRefferals():Promise<any>{
    return this.http.get(this.configUrl).toPromise();
  }

  postRefferall(startDate:string,endDate:string,description:string,doctor:string,user:string,visit:string):Promise<any>{
    return this.http.post(this.configUrl,
      {
        startDate:startDate,
        endDate:endDate,
        description:description,
        doctor:doctor,
        user:user,
        visit:visit
    }).toPromise();
  }

  deleteRefferal(id:number):Promise<any>{
    return this.http.delete(this.configUrl + "/" + id).toPromise();
  }
}
