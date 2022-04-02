import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { MyCoreModule } from 'src/lib/my-core';
import { CommonServicesModule } from '../common-services';
import {
  PedidosAddComponent,
  PedidosEditComponent,
  PedidosListComponent,
  PedidosViewComponent,
  PEDIDOS_COMPONENTES,
} from './pedidos.component';
import { PaginatorModule } from 'primeng/paginator';
import { CommonComponentModule } from '../common-component';
import { CarritoComponent, CARRITO_COMPONENTES } from '../carrito';
const routes: Routes = [
  { path: '', component: PedidosListComponent },
  { path: 'add', component: PedidosAddComponent },
  { path: ':id/edit', component: PedidosEditComponent },
  { path: ':id', component: PedidosViewComponent },
  { path: ':id/:kk', component: PedidosViewComponent },
];
@NgModule({
  declarations: [PEDIDOS_COMPONENTES],
  exports: [
    PEDIDOS_COMPONENTES,
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
export class PedidosModule {}

