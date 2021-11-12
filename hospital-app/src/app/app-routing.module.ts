import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {DrugWarehouseComponent} from "./drug-warehouse/drug-warehouse.component";

const routes: Routes = [
  {path:'',pathMatch: "full",component:DrugWarehouseComponent},
  {path:'login',component:LoginComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
