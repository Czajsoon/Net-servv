import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {templateJitUrl} from "@angular/compiler";
import {AdminPanelService} from "../services/admin-panel.service";
import {Roles} from "../models/Roles";

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.scss']
})
export class AddUserComponent implements OnInit {
  roles:Roles[] = [];
  // @ts-ignore
  newUserForm: FormGroup;

  constructor(private _formBuilder: FormBuilder,
              private adminService: AdminPanelService) { }

  ngOnInit(): void {
    this.adminService.getRoles().then(result =>{
      // @ts-ignore
      this.roles = result;
    })
    this.newUserForm = this._formBuilder.group({
      name: ['',Validators.required],
      surname: ['',Validators.required],
      login: ['',Validators.required],
      password: ['',Validators.required],
      birthDate: ['',Validators.required],
      identification: ['',Validators.required],
      sex: ['',Validators.required],
      selectedRole: [null,Validators.required]
    })
  }

  addUser(){}

}
