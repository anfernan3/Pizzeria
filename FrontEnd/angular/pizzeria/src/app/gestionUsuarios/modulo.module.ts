import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { MyCoreModule } from 'src/lib/my-core';
import { CommonServicesModule } from '../common-services';
import {
  gestionUsuariosEditComponent,
  gestionUsuariosListComponent,
  GESTIONUSURIOS_COMPONENTES,
} from './componente.component';
import { PaginatorModule } from 'primeng/paginator';
import { CommonComponentModule } from '../common-component';

const routes: Routes = [
  { path: '', component: gestionUsuariosListComponent },
  { path: ':username/edit', component: gestionUsuariosEditComponent },
];

@NgModule({
  declarations: [GESTIONUSURIOS_COMPONENTES],
  exports: [GESTIONUSURIOS_COMPONENTES],
  imports: [
    CommonModule,
    FormsModule,
    RouterModule.forChild(routes),
    MyCoreModule,
    CommonServicesModule,
    PaginatorModule,
    CommonComponentModule,
    MyCoreModule,
  ],
})
export class gestionUsuariosModule {}
