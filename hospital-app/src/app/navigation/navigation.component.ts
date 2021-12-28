import { Component, OnInit } from '@angular/core';
import {AuthService} from "../services/auth.service";
import {User} from "../models/user";

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.scss']
})
export class NavigationComponent implements OnInit {

  public user !: User;
  public logged !: boolean;

  constructor(private auth: AuthService) { }

  ngOnInit(): void {
    this.user = this.auth.user;
    this.logged = this.auth.isLoggedIn();
  }

}
