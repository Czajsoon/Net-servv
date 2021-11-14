import {Component, ElementRef, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {AuthService} from "../services/auth.service";

@Component({
  selector: 'app-logowanie',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  visibility = false;
  @ViewChild('login') loginInput:ElementRef | any;
  @ViewChild('password') passwordInput:ElementRef | any;
  constructor(private auth:AuthService) { }

  ngOnInit(): void {

  }

  visibility_change(){
    this.visibility ? this.visibility = false : this.visibility = true;
  }

  performLogin() {
    this.auth.login(this.loginInput.nativeElement.value, this.passwordInput.nativeElement.value)
      .then(data =>{

      })
        .catch(data=>{

        })


  }
}
