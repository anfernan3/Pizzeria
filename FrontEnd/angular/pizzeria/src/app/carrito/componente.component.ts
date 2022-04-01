import { Component, EventEmitter, OnDestroy, OnInit, Output } from '@angular/core';
import { ActivatedRoute, Router, ParamMap } from '@angular/router';
import { CarritoViewModelService } from './servicios.service';

@Component({
  selector: 'app-carrito',
  templateUrl: './tmpl-anfitrion.component.html',
  // providers: [ CarritoViewModelService ],
  styleUrls: ['./componente.component.css']
})
export class CarritoComponent implements OnInit, OnDestroy {
  public pizzasAgregadas: Array<any> = [{}];
  constructor(protected vm: CarritoViewModelService) { }
  public get VM(): CarritoViewModelService { return this.vm; }
  ngOnInit(): void {
    //this.vm.list();
    this.vm.load();
  }
  ngOnDestroy(): void {
    this.vm.clear()
  }
}
@Component({
  selector: 'app-carrito-list',
  templateUrl: './tmpl-list.component.html',
  styleUrls: ['./componente.component.css']
})
export class CarritoListComponent implements OnInit {
  constructor(protected vm: CarritoViewModelService) { }

  public get VM(): CarritoViewModelService { return this.vm; }
  ngOnInit(): void {
    //this.vm.list();
    this.vm.load();
  }
}

@Component({
  selector: 'app-carrito-add',
  templateUrl: './tmpl-form.component.html',
  styleUrls: ['./componente.component.css']
})
export class CarritoAddComponent implements OnInit {
  constructor(protected vm: CarritoViewModelService) { }
  public get VM(): CarritoViewModelService { return this.vm; }
  ngOnInit(): void {
    this.VM.add();
  }
}

@Component({
  selector: 'app-carrito-edit',
  templateUrl: './tmpl-form.component.html',
  styleUrls: ['./componente.component.css']
})
export class CarritoEditComponent implements OnInit, OnDestroy {
  private obs$: any;
  constructor(protected vm: CarritoViewModelService,
    protected route: ActivatedRoute, protected router: Router) { }
  public get VM(): CarritoViewModelService { return this.vm; }
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
  selector: 'app-carrito-view',
  templateUrl: './tmpl-view.component.html',
  styleUrls: ['./componente.component.css']
})
export class CarritoViewComponent implements OnInit, OnDestroy {
  private obs$: any;
  constructor(protected vm: CarritoViewModelService,
    protected route: ActivatedRoute, protected router: Router) { }
  public get VM(): CarritoViewModelService { return this.vm; }
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

export const CARRITO_COMPONENTES = [
  CarritoComponent, CarritoListComponent, CarritoAddComponent,
  CarritoEditComponent, CarritoViewComponent,
];
