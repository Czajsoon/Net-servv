import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MAT_DIALOG_DEFAULT_OPTIONS, MatDialogConfig, MatDialogModule} from "@angular/material/dialog";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {MatIconModule} from "@angular/material/icon";
import {MatTableModule} from "@angular/material/table";
import {MatButtonModule} from "@angular/material/button";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatSnackBarConfig, MatSnackBarModule} from "@angular/material/snack-bar";
import {MatCheckboxModule} from "@angular/material/checkbox";
import {MatSelectModule} from "@angular/material/select";
import {MatCardModule} from "@angular/material/card";

const MAT_DIALOG_GLOBAL_CONFIG: MatDialogConfig = {
  width: "75%",
  hasBackdrop: true
}

@NgModule({
  declarations: [],
  imports: [
    CommonModule,

  ],
  exports: [
    MatIconModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatDialogModule,
    MatButtonModule,
    MatSnackBarModule,
    MatCheckboxModule,
    MatSelectModule,
    MatToolbarModule,
    MatTableModule],
  providers:[
    {provide: MAT_DIALOG_DEFAULT_OPTIONS, useValue: MAT_DIALOG_GLOBAL_CONFIG}
  ]
})
export class MaterialModule { }
