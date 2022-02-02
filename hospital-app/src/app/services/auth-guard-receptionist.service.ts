import { Injectable } from '@angular/core';
import {ActivatedRouteSnapshot, Router, RouterStateSnapshot} from "@angular/router";
import {AuthService} from "./auth.service";

@Injectable({
  providedIn: 'root'
})
export class AuthGuardReceptionistService {

  constructor(private router:Router,
              private authService: AuthService) { }

  canActivate(route: ActivatedRouteSnapshot,state: RouterStateSnapshot):boolean{
    if(!this.authService.doctor){
      alert("Nie masz uprawnień recepcjonisty!");
      this.router.navigate(["home"]);
      return false;
    }
    else return true;
  }
}
