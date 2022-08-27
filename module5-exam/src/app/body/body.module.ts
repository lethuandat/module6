import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {ReactiveFormsModule} from '@angular/forms';
import {BodyComponent} from './body.component';
import {BodyRoutingModule} from './body-routing.module';

@NgModule({
  declarations: [
    BodyComponent
  ],
  imports: [
    CommonModule,
    BodyRoutingModule,
    ReactiveFormsModule
  ]
})
export class BodyModule {
}
