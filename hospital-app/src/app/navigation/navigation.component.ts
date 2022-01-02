import {Component, HostListener, OnInit} from '@angular/core';
import {AuthService} from "../services/auth.service";
import {User} from "../models/user";

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.scss']
})
export class NavigationComponent implements OnInit {
  @HostListener('window:scroll', ['$event'])
  changeScrolled(event:any) {
    this.scrolled = window.pageYOffset>60;
  }

  public user !: User;
  public logged !: boolean;
  public scrolled !: boolean;

  constructor(private auth: AuthService) { }

  ngOnInit(): void {
    this.user = this.auth.user;
    this.logged = this.auth.isLoggedIn();
  }

  scrollPage(){
    this.scrolled = document.body.scrollTop>60;
  }

  onActivate(event:any) {
    let scrollToTop = window.setInterval(() => {
      let pos = window.pageYOffset;
      if (pos > 0) {
        window.scrollTo(0, pos - 20); // how far to scroll on each step
      } else {
        window.clearInterval(scrollToTop);
      }
    }, 9);
  }
}
