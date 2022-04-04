import { Component, EventEmitter, OnDestroy, OnInit, Output } from '@angular/core';
import { ActivatedRoute, Router, ParamMap } from '@angular/router';
import { PedidosViewModelService } from './servicios.service';

@Component({
  selector: 'app-pedidos',
  templateUrl: './tmpl-anfitrion.component.html',
  styleUrls: ['./pedidos.component.css']
})
export class PedidosComponent implements OnInit, OnDestroy {

  constructor(protected vm: PedidosViewModelService) { }
    public get VM(): PedidosViewModelService { return this.vm; }
    ngOnInit(): void {
      //this.vm.list();
      this.vm.load();
    }
    ngOnDestroy(): void {
      this.vm.clear()
      }
    }

  @Component({
    selector: 'app-pedidos-list',
    templateUrl: './tmpl-list.component.html',
    styleUrls: ['./pedidos.component.css']
  })
  export class PedidosListComponent implements OnInit {

    constructor(protected vm: PedidosViewModelService) { }
    public get VM(): PedidosViewModelService { return this.vm; }
    ngOnInit(): void {
      //this.vm.list();
      this.vm.load();
    }
  }

  @Component({
    selector: 'app-pedidos-add',
    templateUrl: './tmpl-formadd.component.html',
    styleUrls: ['./pedidos.component.css']
  })
  export class PedidosAddComponent implements OnInit {
    constructor(protected vm: PedidosViewModelService) { }
    public get VM(): PedidosViewModelService { return this.vm; }
    ngOnInit(): void {
      this.VM.add();
    }
  }

  @Component({
    selector: 'app-pedidos-edit',
    templateUrl: './tmpl-form.component.html',
    styleUrls: ['./pedidos.component.css']
  })
  export class PedidosEditComponent implements OnInit, OnDestroy {
    private obs$: any;
    constructor(protected vm: PedidosViewModelService,
      protected route: ActivatedRoute, protected router: Router) { }
    public get VM(): PedidosViewModelService { return this.vm; }
    ngOnInit(): void {
      this.obs$ = this.route.paramMap.subscribe(
        (params: ParamMap) => {
          const id = parseInt(params?.get('id') ?? '');
          if (id) {
            this.vm.edit(id);
          } else {
            this.router.navigate(['/404.html']);
          }
        });
    }
    ngOnDestroy(): void {
      this.obs$.unsubscribe();
    }
  }

  @Component({
    selector: 'app-pedidos-view',
    templateUrl: './tmpl-view.component.html',
    styleUrls: ['./pedidos.component.css']
  })
  export class PedidosViewComponent implements OnInit, OnDestroy {
    private obs$: any;
    constructor(protected vm: PedidosViewModelService,
      protected route: ActivatedRoute, protected router: Router) { }
    public get VM(): PedidosViewModelService { return this.vm; }
    ngOnInit(): void {
      this.obs$ = this.route.paramMap.subscribe(
        (params: ParamMap) => {
          const id = parseInt(params?.get('id') ?? '');
          if (id) {
            this.vm.view(id);
          } else {
            this.router.navigate(['/404.html']);
          }
        });
    }
    ngOnDestroy(): void {
      this.obs$.unsubscribe();
    }
  }

  export const PEDIDOS_COMPONENTES = [
    PedidosComponent, PedidosListComponent, PedidosAddComponent,
    PedidosEditComponent, PedidosViewComponent,
  ];

  function ngOnDestroy() {
    throw new Error('Function not implemented.');
  }

