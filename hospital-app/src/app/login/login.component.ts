import {Component, OnDestroy, OnInit} from '@angular/core';

@Component({
  selector: 'app-logowanie',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  visibility = false;
  constructor() { }

  ngOnInit(): void {

  }

  visibility_change(){
    this.visibility ? this.visibility = false : this.visibility = true;
  }
}
