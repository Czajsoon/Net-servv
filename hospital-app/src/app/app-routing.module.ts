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
import {SettingsComponent} from "./settings/settings.component";
import {DoctorPanelComponent} from "./doctor-panel/doctor-panel.component";
import {AddTestResultsComponent} from "./add-test-results/add-test-results.component";
import {PatientCardComponent} from "./patient-card/patient-card.component";
import {RefferalMedicalLeaveComponent} from "./refferal-medical-leave/refferal-medical-leave.component";
import {PageNotFoundComponent} from "./page-not-found/page-not-found.component";
import {IssuingPrescriptionsComponent} from "./issuing-prescriptions/issuing-prescriptions.component";
import {NumberPatientComponent} from "./number-patient/number-patient.component";
import {AuthGuardService} from "./services/auth-guard.service";
import {AuthGuardDoctorService} from "./services/auth-guard-doctor.service";
import {AuthGuardReceptionistService} from "./services/auth-guard-receptionist.service";
import {AuthGuardUserService} from "./services/auth-guard-user.service";


const routes: Routes = [
  {path:'',pathMatch: "full",component:HomePageComponent},
  {path:'login',component:LoginComponent},
  {path:'home',component:HomePageComponent},
  {path:'receptionist-panel',component:ReceptionistPanelComponent,canActivate: [AuthGuardReceptionistService,AuthGuardDoctorService,AuthGuardService,AuthGuardReceptionistService]},
  {path:'view-doctors',component:ViewdoctorsComponent},
  {path:'add-test-results',component:AddTestResultsComponent,canActivate: [AuthGuardReceptionistService]},
  {path:'patient-card',component:PatientCardComponent,canActivate: [AuthGuardUserService,AuthGuardDoctorService,AuthGuardService,AuthGuardReceptionistService]},
  {path:'refferal-medical-leave',component:RefferalMedicalLeaveComponent,canActivate: [AuthGuardUserService]},
  {path:'issuing-prescriptions',component:IssuingPrescriptionsComponent},
  {path:'contact',component:NumberPatientComponent},
  {path:'time-table',component:TimetableComponent,resolve:{
      time_table: TimetableResolverService
    }},
  {path:'password-change',component:PasswordChangeComponent,canActivate: [AuthGuardUserService]},
  {path:'patientList', component:PatientlistComponent,canActivate: [AuthGuardUserService]},
  {path:'new-ref',component:NewRefferalAbsentionComponent},
  {path:'list-ref',component:ReferralslistComponent,canActivate: [AuthGuardUserService]},
  {path:'adminPanel',component:AdminPanelComponent,canActivate : [AuthGuardService]},
  {path:'settings',component:SettingsComponent,canActivate: [AuthGuardUserService,AuthGuardDoctorService,AuthGuardService,AuthGuardReceptionistService]},
  {path:'doctor-panel',component:DoctorPanelComponent,canActivate: [AuthGuardDoctorService]},
  {path:'**',component:PageNotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
