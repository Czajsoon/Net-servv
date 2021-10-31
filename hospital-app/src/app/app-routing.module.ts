import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LogowanieComponent} from "./logowanie/logowanie.component";
import {DrugWarehouseComponent} from "./drug-warehouse/drug-warehouse.component";

const routes: Routes = [
  {path:'',pathMatch: "full",component:DrugWarehouseComponent},
  {path:'login',component:LogowanieComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
