import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { MyCoreModule } from 'src/lib/my-core';
import { CommonServicesModule } from '../common-services';
import { PizzasComponent, PIZZAS_COMPONENTES } from './componente.component';
import {PaginatorModule} from 'primeng/paginator';
import { CommonComponentModule } from '../common-component';

@NgModule({
  declarations: [
    PIZZAS_COMPONENTES,
  ],
  exports: [
    PIZZAS_COMPONENTES,
    // PizzasComponent,
  ],
  imports: [
    CommonModule, FormsModule, RouterModule.forChild([]),
    MyCoreModule, CommonServicesModule,
    PaginatorModule, CommonComponentModule, MyCoreModule,
  ]
})
export class PizzasModule { }
