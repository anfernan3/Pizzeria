import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PedidosComponent, PEDIDOS_COMPONENTES } from './pedidos.component';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { MyCoreModule } from 'src/lib/my-core';
import { CommonServicesModule } from '../common-services';
import { PaginatorModule } from 'primeng/paginator';
import { CommonComponentModule } from '../common-component';



@NgModule({
  declarations: [
    PedidosComponent
  ],
  ,
  exports: [
    PEDIDOS_COMPONENTES,
  ],
  imports: [
    CommonModule, FormsModule, RouterModule.forChild([]),
    MyCoreModule, CommonServicesModule,
    PaginatorModule, CommonComponentModule, MyCoreModule,
  ]
})
export class PedidosModule { }
