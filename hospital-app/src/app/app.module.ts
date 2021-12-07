import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { LoginComponent } from './login/login.component';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import {MatIconModule} from "@angular/material/icon";
import { DrugWarehouseComponent } from './drug-warehouse/drug-warehouse.component';
import {HttpClientModule} from "@angular/common/http";
import {MatTableModule} from "@angular/material/table";
import {MatButtonModule} from "@angular/material/button";
import { NewDrugComponent } from './new-drug/new-drug.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatDialogModule} from "@angular/material/dialog";
import {MaterialModule} from "./material/material.module";
import { HomePageComponent } from './home-page/home-page.component';
import { NurseTimetableComponent } from './nurse-timetable/nurse-timetable.component';
import { PasswordChangeComponent } from './password-change/password-change.component';
import { PatientlistComponent } from './patientlist/patientlist.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    DrugWarehouseComponent,
    NewDrugComponent,
    HomePageComponent,
    NurseTimetableComponent,
    PasswordChangeComponent,
    PatientlistComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    NgbModule,
    ReactiveFormsModule,
    FormsModule,
    MaterialModule
  ],
  providers: [],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  bootstrap: [AppComponent]
})
export class AppModule { }
