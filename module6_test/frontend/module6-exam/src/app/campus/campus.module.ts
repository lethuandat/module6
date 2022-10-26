import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {CampusRoutingModule} from './campus-routing.module';
import {CampusListComponent} from './campus-list/campus-list.component';
import {CampusCreateComponent} from './campus-create/campus-create.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";


@NgModule({
  declarations: [CampusListComponent, CampusCreateComponent],
  imports: [
    CommonModule,
    CampusRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class CampusModule { }
