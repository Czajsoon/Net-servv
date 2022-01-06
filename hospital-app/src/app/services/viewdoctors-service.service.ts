import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {BackendConnectService} from "./backend-connect.service";

@Injectable({
  providedIn: 'root'
})
export class ViewdoctorsServiceService {

  constructor(
    private http:HttpClient,
    private backendconnect:BackendConnectService
  ) { }

  getDoctors(spec:string) {
    return this.http.get(this.backendconnect.getEndpoint()+"api/doctors?spec="+spec).toPromise();
  }

}
