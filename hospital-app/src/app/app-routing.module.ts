import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LogowanieComponent} from "./logowanie/logowanie.component";
import {DrugWarehouseComponent} from "./drug-warehouse/drug-warehouse.component";

const routes: Routes = [
  {path:'',pathMatch: "full",component:LogowanieComponent},
  {path:'drug-warehouse',component:DrugWarehouseComponent}



];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
