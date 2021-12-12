import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {BackendConnectService} from "./backend-connect.service";

@Injectable({
  providedIn: 'root'
})
export class TimetableService {

  private endPoint:string ="api/activity"

  constructor(private http:HttpClient,
              private backend:BackendConnectService) { }

  async getActivities():Promise<any> {
    return this.http.get(this.backend.getEndpoint() + this.endPoint).toPromise();
  }
}
