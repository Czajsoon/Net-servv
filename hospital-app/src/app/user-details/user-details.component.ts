import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from "@angular/material/dialog";
import {User} from "../models/user";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AdminPanelService} from "../services/admin-panel.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {DatePipe} from "@angular/common";

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.scss']
})
export class UserDetailsComponent implements OnInit {
  // @ts-ignore
  userBornDate:Date | null = new Date();
  // @ts-ignore
  detailsForm:FormGroup;
  // @ts-ignore
  passwordChange:FormGroup;

  constructor(@Inject(MAT_DIALOG_DATA) public user: User,
              private _formBuilder:FormBuilder,
              private dialog: MatDialogRef<UserDetailsComponent>,
              private adminService: AdminPanelService,
              private snackBar: MatSnackBar,
              private datePipe: DatePipe) {
    this.userBornDate = new Date(this.user.bornDate);
  }

  ngOnInit(): void {
    this.detailsForm = this._formBuilder.group({
      name: [this.user.name, Validators.required],
      surname: [this.user.surname, Validators.required],
      bornDate: [this.userBornDate, Validators.required],
      identification: [this.user.identification, Validators.required],
      login: [this.user.username, Validators.required],
      sex: [this.user.sex, Validators.required]
    });
    this.passwordChange = this._formBuilder.group({
      password: ['',Validators.required]
    })
  }

  changePassword(){
    this.adminService.forcePassword(this.passwordChange.get("password")?.value,this.user.id).catch(reason =>{
      if(reason.error.text == "Hasło użytkownika zostało zaktualizowane"){
        this.snackBar.open("Hasło użytkownika zostało zaktualizowane","",{
          panelClass: ['success']
        });
      }
      else{
        this.snackBar.open("Uzytkownik nie znaleziony!","",{
          panelClass: ['failure']
        });
      }
    })
  }

  updateUser(){
    this.userBornDate = new Date(this.detailsForm.get("bornDate")?.value);
    let eddited = {
      username:this.detailsForm.get("login")?.value,
      name:this.detailsForm.get("name")?.value,
      surname:this.detailsForm.get("surname")?.value,
      bornDate:this.datePipe.transform(this.userBornDate,"yyyy-MM-dd"),
      identification:this.detailsForm.get("identification")?.value,
      sex:this.detailsForm.get("sex")?.value};
    this.adminService.editUser(eddited,this.user.id).catch(reason => {
      if(reason.error.text == "Użytkownik został zaktualizowany"){
        this.snackBar.open("Użytkownik został zaktualizowany","",{
          panelClass: ['success']
        });
        this.dialog.close();
      }
      else{
        this.snackBar.open("Uzytkownik nie znaleziony!","",{
          panelClass: ['failure']
        });
        this.dialog.close();
      }
    });

  }

  deleteUser(){
    this.adminService.deleteUser(this.user.id).then().catch(reason => {
      if(reason.error.text == "Użytkownik Pomyślnie usunięty!"){
        this.snackBar.open("Użytkownik Pomyślnie usunięty!","",{
          panelClass: ['success']
        });
        this.dialog.close();
      }
    });
  }

}
