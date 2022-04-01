// Importar los módulos del router de angular
import { RouterModule, Routes } from '@angular/router';

// Importar componentes a los cuales les quiero hacer una página exclusiva
import { ChefComponent } from './chef/chef.component';
import { PromocionesComponent } from './promociones/promociones.component';
import { ErrorComponent } from './error/error.component';
import { NgModule } from '@angular/core';
import { MainimageComponent } from './mainimage/mainimage.component';
import { ComentariosComponent } from './comentarios/componente.component';
import { PizzasAddComponent, PizzasComponent, PizzasEditComponent, PizzasListComponent, PizzasViewComponent } from './pizzas';
import { LoginComponent, RegisterUserComponent } from './security';


// Array de rutas
const routes: Routes = [
  {path: '', component: MainimageComponent},
  {path: 'chef', component: ChefComponent},
  {path: 'promociones', component: PromocionesComponent},
  {path: 'comentarios', component: ComentariosComponent},
  {path: 'login', component: LoginComponent},
  {path: 'carta', loadChildren: () => import('./pizzas/modulo.module').then(mod => mod.PizzasModule)},
  {path: 'ingredientes', loadChildren: () => import('./ingredientes/modulo.module').then(mod => mod.IngredientesModule) },
  {path: 'registro', component: RegisterUserComponent},
  {path: '**', component: ErrorComponent},
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
