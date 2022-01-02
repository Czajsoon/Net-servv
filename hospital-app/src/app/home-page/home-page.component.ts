import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {BloodResult, BloodResults} from "../models/BloodResults";
import {ActivatedRoute} from "@angular/router";
import {AuthService} from "../services/auth.service";

let results:BloodResult[] = [];

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.scss']
})
export class HomePageComponent implements OnInit {

  public bloodResults = [...results];

  constructor(private http:HttpClient,
              private act:ActivatedRoute,
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
