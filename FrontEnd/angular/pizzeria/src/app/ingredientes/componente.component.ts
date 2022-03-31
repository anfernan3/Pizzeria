// import { Component, EventEmitter, OnDestroy, OnInit, Output } from '@angular/core';
// import { ActivatedRoute, Router, ParamMap } from '@angular/router';
// import { IngredientesViewModelService } from './servicios.service';

// @Component({
//   selector: 'app-ingredientes',
//   templateUrl: './tmpl-anfitrion.component.html',
//   // providers: [ IngredientesViewModelService ],
//   styleUrls: ['./componente.component.css']
// })
// export class IngredientesComponent implements OnInit, OnDestroy {
//   constructor(protected vm: IngredientesViewModelService) { }
//   public get VM(): IngredientesViewModelService { return this.vm; }
//   ngOnInit(): void {
//     //this.vm.list();
//     this.vm.load();
//   }
//   ngOnDestroy(): void {
//     this.vm.clear()
//   }
// }
// @Component({
//   selector: 'app-ingredientes-list',
//   templateUrl: './tmpl-list.component.html',
//   styleUrls: ['./componente.component.css']
// })
// export class IngredientesListComponent implements OnInit {
//   constructor(protected vm: IngredientesViewModelService) { }
//   public get VM(): IngredientesViewModelService { return this.vm; }
//   ngOnInit(): void {
//     //this.vm.list();
//     this.vm.load();
//   }
// }

// @Component({
//   selector: 'app-ingredientes-add',
//   templateUrl: './tmpl-form.component.html',
//   styleUrls: ['./componente.component.css']
// })
// export class IngredientesAddComponent implements OnInit {
//   constructor(protected vm: IngredientesViewModelService) { }
//   public get VM(): IngredientesViewModelService { return this.vm; }
//   ngOnInit(): void {
//     this.VM.add();
//   }
// }

// @Component({
//   selector: 'app-ingredientes-edit',
//   templateUrl: './tmpl-form.component.html',
//   styleUrls: ['./componente.component.css']
// })
// export class IngredientesEditComponent implements OnInit, OnDestroy {
//   private obs$: any;
//   constructor(protected vm: IngredientesViewModelService,
//     protected route: ActivatedRoute, protected router: Router) { }
//   public get VM(): IngredientesViewModelService { return this.vm; }
//   ngOnInit(): void {
//     this.obs$ = this.route.paramMap.subscribe(
//       (params: ParamMap) => {
//         const id = parseInt(params?.get('id') ?? '');
//         if (id) {
//           this.vm.edit(id);
//         } else {
//           this.router.navigate(['/404.html']);
//         }
//       });
//   }
//   ngOnDestroy(): void {
//     this.obs$.unsubscribe();
//   }
// }

// @Component({
//   selector: 'app-ingredientes-view',
//   templateUrl: './tmpl-view.component.html',
//   styleUrls: ['./componente.component.css']
// })
// export class IngredientesViewComponent implements OnInit, OnDestroy {
//   private obs$: any;
//   constructor(protected vm: IngredientesViewModelService,
//     protected route: ActivatedRoute, protected router: Router) { }
//   public get VM(): IngredientesViewModelService { return this.vm; }
//   ngOnInit(): void {
//     this.obs$ = this.route.paramMap.subscribe(
//       (params: ParamMap) => {
//         const id = parseInt(params?.get('id') ?? '');
//         if (id) {
//           this.vm.view(id);
//         } else {
//           this.router.navigate(['/404.html']);
//         }
//       });
//   }
//   ngOnDestroy(): void {
//     this.obs$.unsubscribe();
//   }
// }

// export const INGREDIENTES_COMPONENTES = [
//   IngredientesComponent, IngredientesListComponent, IngredientesAddComponent,
//   IngredientesEditComponent, IngredientesViewComponent,
// ];
