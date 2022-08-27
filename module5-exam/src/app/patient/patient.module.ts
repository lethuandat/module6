import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {PatientRoutingModule} from './patient-routing.module';
import {PatientListComponent} from './patient-list/patient-list.component';
import {NgbPaginationModule} from '@ng-bootstrap/ng-bootstrap';
import {PatientEditComponent} from './patient-edit/patient-edit.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { PatientCreateComponent } from './patient-create/patient-create.component';


@NgModule({
  declarations: [PatientListComponent, PatientEditComponent, PatientCreateComponent],
  imports: [
    CommonModule,
    PatientRoutingModule,
    NgbPaginationModule,
    ReactiveFormsModule,
    FormsModule
  ]
})
export class PatientModule {
}
