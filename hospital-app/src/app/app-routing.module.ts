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
import {ReferralslistComponent} from "./referralslist/referralslist.component";
import {ReceptionistPanelComponent} from "./recepcionist-panel/receptionist-panel.component";
import {ViewdoctorsComponent} from "./viewdoctors/viewdoctors.component";
import {AdminPanelComponent} from "./admin-panel/admin-panel.component";


const routes: Routes = [
  {path:'',pathMatch: "full",component:HomePageComponent},
  {path:'login',component:LoginComponent},
  {path:'home',component:HomePageComponent},
  {path:'receptionist-panel',component:ReceptionistPanelComponent},
  {path:'view-doctors',component:ViewdoctorsComponent},
  {path:'time-table',component:TimetableComponent,resolve:{
    time_table: TimetableResolverService
    }},
  {path:'password-change',component:PasswordChangeComponent},
  {path:'patientList', component:PatientlistComponent},
  {path:'new-ref',component:NewRefferalAbsentionComponent},
  {path:'list-ref',component:ReferralslistComponent},
  {path:'adminPanel',component:AdminPanelComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
