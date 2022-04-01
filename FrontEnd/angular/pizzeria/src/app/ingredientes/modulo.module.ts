import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { MyCoreModule } from 'src/lib/my-core';
import { CommonServicesModule } from '../common-services';
import {
  IngredientesAddComponent,
  IngredientesEditComponent,
  IngredientesListComponent,
  IngredientesViewComponent,
  INGREDIENTES_COMPONENTES,
} from './componente.component';
import { PaginatorModule } from 'primeng/paginator';
import { CommonComponentModule } from '../common-component';

const routes: Routes = [
  { path: '', component: IngredientesListComponent },
  { path: 'add', component: IngredientesAddComponent },
  { path: ':id/edit', component: IngredientesEditComponent },
  { path: ':id', component: IngredientesViewComponent },
  { path: ':id/:kk', component: IngredientesViewComponent },
];

@NgModule({
  declarations: [INGREDIENTES_COMPONENTES],
  exports: [
    INGREDIENTES_COMPONENTES,

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
export class IngredientesModule {}
