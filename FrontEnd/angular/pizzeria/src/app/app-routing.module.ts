// Importar los módulos del router de angular
import { RouterModule, Routes } from '@angular/router';
import { ModuleWithProviders } from '@angular/core';

// Importar componentes a los cuales les quiero hacer una página exclusiva
import { CartaComponent } from './carta/carta.component';
import { ChefComponent } from './chef/chef.component';
import { PromocionesComponent } from './promociones/promociones.component';
import { AppComponent } from './app.component';

// Array de rutas
const appRoutes: Routes = [
  {path: '', component: AppComponent},
  {path: 'carta', component: CartaComponent},
  {path: 'chef', component: ChefComponent},
  {path: 'promociones', component: PromocionesComponent},
];

//Exportar el módulo de rutas

export const appRoutingProviders: any[] = [];
export const routing: ModuleWithProviders<any> = RouterModule.forRoot(appRoutes);

//   @Component({
//     selector: 'selector-name',
//     templateUrl: 'name.component.html'
//   })

//   export class NameComponent implements OnInit {
//     constructor() { }

//     ngOnInit() { }
//   }}
// ]

// const routes: Routes = [];

// @NgModule({
//   imports: [RouterModule.forRoot(routes)],
//   exports: [RouterModule]
// })
// export class AppRoutingModule { }
