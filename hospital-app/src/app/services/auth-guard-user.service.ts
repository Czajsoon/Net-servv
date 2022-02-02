import { Injectable } from '@angular/core';
import {ActivatedRouteSnapshot, Router, RouterStateSnapshot} from "@angular/router";
import {AuthService} from "./auth.service";

@Injectable({
  providedIn: 'root'
})
export class AuthGuardUserService {

  constructor(private router:Router,
              private authService: AuthService) { }

  canActivate(route: ActivatedRouteSnapshot,state: RouterStateSnapshot):boolean{
    if(!this.authService.userloged){
      alert("Nie masz uprawnień użytkownika!");
      this.router.navigate(["home"]);
      return false;
    }
    else return true;
  }
}
