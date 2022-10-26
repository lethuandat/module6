import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {CustomerUpdateComponent} from "./customer-update/customer-update.component";
import {CustomerCreateComponent} from "./customer-create/customer-create.component";
import {CustomerListComponent} from "./customer-list/customer-list.component";


const routes: Routes = [
  {
    path: 'customer/list',
    component: CustomerListComponent
  }, {
    path: 'customer/create',
    component: CustomerCreateComponent
  }, {
    path: 'customer/edit/:id',
    component: CustomerUpdateComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CustomerRoutingModule { }
