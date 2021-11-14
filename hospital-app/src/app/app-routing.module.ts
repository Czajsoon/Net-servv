import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {DrugWarehouseComponent} from "./drug-warehouse/drug-warehouse.component";
import {HomePageComponent} from "./home-page/home-page.component";

const routes: Routes = [
  {path:'',pathMatch: "full",component:DrugWarehouseComponent},
  {path:'login',component:LoginComponent},
  {path:'home',component:HomePageComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
