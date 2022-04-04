import { NgModule, NO_ERRORS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CarritoComponent, CarritoListComponent, CARRITO_COMPONENTES } from './componente.component';
import { FormsModule } from '@angular/forms';
import { Routes, RouterModule } from '@angular/router';
import { PaginatorModule } from 'primeng/paginator';
import { MyCoreModule } from 'src/lib/my-core';
import { CommonComponentModule } from '../common-component';
import { CommonServicesModule } from '../common-services';

const routes: Routes = [
  { path: '', component: CarritoListComponent },
];

@NgModule({
  declarations: [
    CARRITO_COMPONENTES
  ],
  exports: [
    CARRITO_COMPONENTES
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
  ]
})
export class CarritoModule { }
