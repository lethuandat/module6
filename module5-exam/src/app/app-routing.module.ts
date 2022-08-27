import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';


const routes: Routes = [
  {
    path: '',
    loadChildren: () => import('./body/body.module').then(module => module.BodyModule)
  },
  {
    path: 'patient',
    loadChildren: () => import('./patient/patient.module').then(module => module.PatientModule)
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
