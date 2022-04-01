import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { MyCoreModule } from 'src/lib/my-core';
import { CommonServicesModule } from '../common-services';
import {
  CarritoAddComponent,
  CarritoEditComponent,
  CarritoListComponent,
  CarritoViewComponent,
  CARRITO_COMPONENTES,
} from './componente.component';
import { PaginatorModule } from 'primeng/paginator';
import { CommonComponentModule } from '../common-component';
const routes: Routes = [
  { path: '', component: CarritoListComponent },
  { path: 'add', component: CarritoAddComponent },
  { path: ':id', component: CarritoViewComponent },
  { path: ':id/edit', component: CarritoEditComponent },
  { path: ':id/:kk', component: CarritoViewComponent },
];
@NgModule({
  declarations: [CARRITO_COMPONENTES],
  exports: [
    CARRITO_COMPONENTES,
  ],
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
export class CarritoModule {}
