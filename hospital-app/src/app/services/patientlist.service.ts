import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {BackendConnectService} from "./backend-connect.service";

@Injectable({
  providedIn: 'root'
})
export class PatientlistService{

  private readonly configUrl:string;
  private readonly configUrlRegister:string;

  constructor(private backend:BackendConnectService,
              private http:HttpClient) {
    this.configUrl = backend.getEndpoint() + "api/users";
    this.configUrlRegister=backend.getEndpoint()+"register";
  }
  getPatients():Promise<any>{
    return this.http.get(this.configUrl).toPromise();
  }

  postPatient(password:string,username:string, name:string, surname:string,identification:string,bornDate:string):Promise<any>{
    return this.http.post(this.configUrlRegister,
      {
        password:password,
        username:username,
        name:name,
        surname:surname,
        bornDate:bornDate,
        identification:identification,
      }).toPromise();
  }

  deletePatient(id:number):Promise<any>{
    return this.http.delete(this.configUrl + "/" + id).toPromise();
  }

}
