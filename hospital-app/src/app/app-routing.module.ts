import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {DrugWarehouseComponent} from "./drug-warehouse/drug-warehouse.component";
import {HomePageComponent} from "./home-page/home-page.component";
import {TimetableComponent} from "./timetable/timetable.component";
import {TimetableResolverService} from "./services/timetable-resolver.service";
import {PasswordChangeComponent} from "./password-change/password-change.component";
import {PatientlistComponent} from "./patientlist/patientlist.component";
import {NewRefferalAbsentionComponent} from "./new-refferal-absention/new-refferal-absention.component";

const routes: Routes = [
  {path:'',pathMatch: "full",component:DrugWarehouseComponent},
  {path:'login',component:LoginComponent},
  {path:'home',component:HomePageComponent},
  {path:'time-table',component:TimetableComponent,resolve:{
    time_table: TimetableResolverService
    }},
  {path:'password-change',component:PasswordChangeComponent},
  {path:'patientList', component:PatientlistComponent},
  {path:'new-ref',component:NewRefferalAbsentionComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
