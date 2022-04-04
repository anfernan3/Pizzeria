import { Component, OnDestroy, OnInit } from '@angular/core';
import { Pizza } from '../pizzas/servicios.service';
import { CarritoViewModelService } from './servicios.service';

@Component({
  selector: 'app-carrito',
  templateUrl: './tmpl-anfitrion.component.html',
  styleUrls: ['./componente.component.css']
})
export class CarritoComponent implements OnInit, OnDestroy {
  constructor(protected vm: CarritoViewModelService) { }
  public get VM(): CarritoViewModelService { return this.vm; }

  ngOnInit(): void {
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
  public pizzasCarrito:Array<any> = [
    {}]
  cantidad: number = 1;
  constructor(protected vm: CarritoViewModelService) { }
  public get VM(): CarritoViewModelService { return this.vm; }
  ngOnInit(): void {
    //this.vm.list();
    this.vm.load();
  }
}

export const CARRITO_COMPONENTES = [
  CarritoComponent, CarritoListComponent
];
