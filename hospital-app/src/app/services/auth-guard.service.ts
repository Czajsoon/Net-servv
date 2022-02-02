import { Injectable } from '@angular/core';
import {ActivatedRoute, ActivatedRouteSnapshot, Router, RouterStateSnapshot} from "@angular/router";
import {AuthService} from "./auth.service";

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService {

  constructor(private router:Router,
              private authService: AuthService) { }

  // @ts-ignore
  canActivate(route: ActivatedRouteSnapshot,state: RouterStateSnapshot):boolean{
    if(!this.authService.admin){
      alert("Nie masz uprawnień admina!");
      this.router.navigate(["home"]);
      return false;
    }
    else return true;
  }

}
