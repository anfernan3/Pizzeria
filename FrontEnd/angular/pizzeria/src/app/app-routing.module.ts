// Importar los módulos del router de angular
import { RouterModule, Routes } from '@angular/router';

// Importar componentes a los cuales les quiero hacer una página exclusiva
import { CartaComponent } from './carta/carta.component';
import { ChefComponent } from './chef/chef.component';
import { PromocionesComponent } from './promociones/promociones.component';
import { ErrorComponent } from './error/error.component';
import { NgModule } from '@angular/core';


// Array de rutas
const routes: Routes = [
  {path: '', component: CartaComponent},
  {path: 'carta', component: CartaComponent},
  {path: 'chef', component: ChefComponent},
  {path: 'promociones', component: PromocionesComponent},
  {path : '**', component: ErrorComponent}
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
