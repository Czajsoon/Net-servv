import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {BackendConnectService} from "./backend-connect.service";
import {VisitReq} from "../models/VisitReq";
import {resolve} from "@angular/compiler-cli/src/ngtsc/file_system";

@Injectable({
  providedIn: 'root'
})
export class ReceptionistService {

  private requestURI:string = "api/visits/receptionist";

  constructor(private http: HttpClient,
              private backend: BackendConnectService) { }

  getSpecialisations(){
    return this.http.get(this.backend.getEndpoint() + "api/specialisation").toPromise();
  }

  getVisits(spec:string,dateStart:Date | null,dateEnd:Date | null,surnameDoc:string,surnamePat:string){
    let request = this.requestURI;
    if (spec.length==0) request+= "?spec=Wszystkie";
    else request+= "?spec="+spec;
    if (dateStart != null) request += "&date=" + dateStart.toISOString().substr(0,dateStart.toISOString().length-5);
    if (dateEnd != null) request += "&endDate=" + dateEnd.toISOString().substr(0,dateEnd.toISOString().length-5);
    if (surnameDoc.length != 0 ) request += "&doctorSurname=" + surnameDoc;
    if (surnamePat.length != 0) request += "&userSurname=" + surnamePat;
    return this.http.get(this.backend.getEndpoint() + request).toPromise();
  }

  addVisit(visit:VisitReq){
    return this.http.post(this.backend.getEndpoint() + "api/visits",visit).toPromise();
  }

  editVisit(editedVisit: any){
    return this.http.put(this.backend.getEndpoint() + "api/visits",editedVisit).toPromise();
  }

  cancelVisit(visitId:number){
    return this.http.delete(this.backend.getEndpoint() + "api/visits?visitId=" + visitId).toPromise();
  }
}
