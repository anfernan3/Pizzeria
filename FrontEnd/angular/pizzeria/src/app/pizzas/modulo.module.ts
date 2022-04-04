import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { MyCoreModule } from 'src/lib/my-core';
import { CommonServicesModule } from '../common-services';
import {
  PizzasAddComponent,
  PizzasEditComponent,
  PizzasListComponent,
  PizzasViewComponent,
  PIZZAS_COMPONENTES,
} from './componente.component';
import { PaginatorModule } from 'primeng/paginator';
import { CommonComponentModule } from '../common-component';

const routes: Routes = [
  { path: '', component: PizzasListComponent },
  { path: 'add', component: PizzasAddComponent },
  { path: ':id', component: PizzasViewComponent },
  { path: ':id/edit', component: PizzasEditComponent },
  { path: ':id/:kk', component: PizzasViewComponent },
];
@NgModule({
  declarations: [PIZZAS_COMPONENTES],
  exports: [
    PIZZAS_COMPONENTES,
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
export class PizzasModule {}
