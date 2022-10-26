import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CampusListComponent} from "./campus-list/campus-list.component";
import {CampusCreateComponent} from "./campus-create/campus-create.component";


const routes: Routes = [
  {
    path: 'campus/list',
    component: CampusListComponent
  }, {
    path: 'campus/create',
    component: CampusCreateComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CampusRoutingModule { }
