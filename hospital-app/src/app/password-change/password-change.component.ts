import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';


import {FormBuilder,FormGroup,Validators} from "@angular/forms";
import {MustMatch} from "../validators/MustMatch";
import {MatSnackBar} from "@angular/material/snack-bar";
import {PasswordChangeService} from "../services/password-change.service";
import {SettingsComponent} from "../settings/settings.component";
import {AuthService} from "../services/auth.service";


@Component({
  selector: 'app-password-change',
  templateUrl: './password-change.component.html',
  styleUrls: ['./password-change.component.scss']
})
export class PasswordChangeComponent implements OnInit {
  visibility = false;
  @ViewChild('repPassword1') newPasswordInput: ElementRef | any;
  @ViewChild('repPassword2') repNewPasswordInput: ElementRef | any;
  @ViewChild('password') oldPasswordInput: ElementRef | any;
  form: FormGroup | any;

  constructor(private formBuilder: FormBuilder,
              private toast: MatSnackBar,
              private pass_serv: PasswordChangeService,
              private auth: AuthService
              ) {

  }

  ngOnInit(): void {
  this.buildForm();

  }

  buildForm(){
    this.form = this.formBuilder.group({
      actualPassword:['',Validators.required],
      newPassword:['',[Validators.required,Validators.minLength(8),Validators.maxLength(20)]],
      repeatPassword:['',[Validators.required,Validators.minLength(8),Validators.maxLength(20)]]
    },{
      validator: MustMatch('newPassword','repeatPassword')
    });
  }

  visibility_change(){
    this.visibility ? this.visibility = false : this.visibility = true;
  }

  changePassword(){
let id1=this.auth.user.id.toString();
      this.pass_serv.postChange(id1,
        this.oldPasswordInput.nativeElement.value,
        this.newPasswordInput.nativeElement.value
      )
    }
}
