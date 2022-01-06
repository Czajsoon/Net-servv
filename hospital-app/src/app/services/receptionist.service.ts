import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {BackendConnectService} from "./backend-connect.service";

@Injectable({
  providedIn: 'root'
})
export class ReceptionistService {

  constructor(private http: HttpClient,
              private backend: BackendConnectService) { }

  getSpecialisations(){
    return this.http.get(this.backend.getEndpoint() + "api/specialisation").toPromise();
  }

  getVisits(spec:string,dateStart:Date | null,dateEnd:Date | null,surnameDoc:string,surnamePat:string){
    let request = "api/visits/receptionist";
    if (spec.length==0) request+= "?spec=Wszystkie";
    else request+= "?spec="+spec;
    if (dateStart != null) request += "&date=" + dateStart.toISOString().substr(0,dateStart.toISOString().length-5);
    if (dateEnd != null) request += "&endDate=" + dateEnd.toISOString().substr(0,dateEnd.toISOString().length-5);
    if (surnameDoc.length != 0 ) request += "&doctorSurname=" + surnameDoc;
    if (surnamePat.length != 0) request += "&userSurname=" + surnamePat;
    console.log(request);
    return this.http.get(this.backend.getEndpoint() + request).toPromise();
  }
}
