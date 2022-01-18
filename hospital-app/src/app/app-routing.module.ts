import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {DrugWarehouseComponent} from "./drug-warehouse/drug-warehouse.component";
import {HomePageComponent} from "./home-page/home-page.component";
import {TimetableComponent} from "./timetable/timetable.component";
import {TimetableResolverService} from "./services/timetable-resolver.service";
import {ReceptionistPanelComponent} from "./recepcionist-panel/receptionist-panel.component";
import {ViewdoctorsComponent} from "./viewdoctors/viewdoctors.component";
import {AddTestResultsComponent} from "./add-test-results/add-test-results.component";
import {PatientCardComponent} from "./patient-card/patient-card.component";

const routes: Routes = [
  {path:'',pathMatch: "full",component:HomePageComponent},
  {path:'login',component:LoginComponent},
  {path:'home',component:HomePageComponent},
  {path:'receptionist-panel',component:ReceptionistPanelComponent},
  {path:'view-doctors',component:ViewdoctorsComponent},
  {path:'add-test-results',component:AddTestResultsComponent},
  {path:'patient-card',component:PatientCardComponent},
  {path:'time-table',component:TimetableComponent,resolve:{
    time_table: TimetableResolverService
    }}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
