import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AuthService} from "../services/auth.service";
import {BloodResult} from "../models/BloodResult";

let results:BloodResult[] = [];

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.scss']
})
export class HomePageComponent implements OnInit {

  public bloodResults = [...results];

  constructor(private http:HttpClient,
              public auth:AuthService) {
    if(auth.isLoggedIn()){
      this.http.get("http://localhost:8080/api/blood").subscribe(results =>{
        console.log(results);
        if(results!=null)
          // @ts-ignore
          this.bloodResults = [...results.bloodElements]
      });
    }
  }

  ngOnInit(): void {
  }


}
