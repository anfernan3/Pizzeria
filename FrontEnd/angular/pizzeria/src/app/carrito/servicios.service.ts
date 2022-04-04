import { HttpClient, HttpContext } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { Observable } from "rxjs";
import { LoggerService } from "src/lib/my-core";
import { RESTDAOService } from "../comentarios/RESTDAOService";
import { ModoCRUD } from "../comentarios/servicios.service";
import { NotificationService, NavigationService } from "../common-services";
import { Ingrediente, IngredientesDAOService } from "../ingredientes/servicios.service";
import { Pizza } from "../pizzas/servicios.service";
import { AuthService, AUTH_REQUIRED } from "../security";

export class Carrito {
  constructor(public pizza: Pizza, public cantidad: number) {}
  public get precio() { return (this.pizza.precio??0) * this.cantidad; }
}
@Injectable({
  providedIn: 'root'
})
export class CarritoService {
  public pizzasCarrito:Array<Carrito> = []
  /*
  public pizzasCarrito:Array<any> = [
    {
      id: 1,
      nombre: 'Pizza Barbacoa',
      descripcion:'Deliciosa pizza barbacoa con los mejores ingredientes',
      fotoUrl:'../assets/img/pizzas/barbacoa.webp',
      precio: 10,
      gusta:10
    }]
    */


  constructor() { }


  agregarAlCarrito(elemento: Pizza, cantidad: number = 1){
    if(!elemento) return;
    const pizzaEnCarrito = this.pizzasCarrito.find(item => item.pizza.id == elemento.id)
    if(pizzaEnCarrito) {
      pizzaEnCarrito.cantidad++
    } else {
      this.pizzasCarrito.push(new Carrito(elemento, cantidad));
    }
  }

  quitarDelCarrito(indice: number){
    this.pizzasCarrito.splice(indice, 1);
  }

  limpiaCarrito() {
    this.pizzasCarrito = []
  }
}

@Injectable({
  providedIn: 'root'
})
export class CarritoDAOService extends RESTDAOService<any, any> {
  constructor(http: HttpClient) {
    super(http, 'carrito', { context: new HttpContext().set(AUTH_REQUIRED, true) });
  }
  override query(tipo: string = ''): Observable<Array<any>> {
    return this.http.get<Array<any>>(`${this.baseUrl + tipo}`, this.option);
  }

  page(page: number, rows: number = 20, tipo: string = ''): Observable<{ page: number, pages: number, rows: number, list: Array<any> }> {
    return new Observable(subscriber => {
      this.http.get<any>(`${this.baseUrl + tipo}?page=${page}&size=${rows}&sort=nombre`, this.option)
        .subscribe({
          next: data => {
            if (page >= data.pages) page = data.pages > 0 ? data.pages - 1 : 0;
            subscriber.next({ page: data.number, pages: data.totalPages, rows: data.totalElements, list: data.content })
          },
          error: err => subscriber.error(err)
        })
    })
  }
}




@Injectable({
  providedIn: 'root'
})
export class CarritoViewModelService {
  protected modo: ModoCRUD = 'list';
  protected listado: Array<any> = [];
  protected elemento: any = {};
  protected idOriginal: any = null;
  protected listURL = '/carrito';

  constructor(protected notify: NotificationService, protected out: LoggerService, protected dao: CarritoDAOService,
    public auth: AuthService, protected router: Router, private navigation: NavigationService) { }

  public get Modo(): ModoCRUD { return this.modo; }
  public get Listado(): Array<any> { return this.listado; }
  public get Elemento(): any { return this.elemento; }
  public get isAutenticated(): boolean { return this.auth.isAutenticated; }

  public list(): void {
    this.dao.query(this.tipo).subscribe({
      next: data => {
        this.listado = data;
        this.modo = 'list';
      },
      error: err => this.notify.add(err.message)
    });
  }

  public add(): void {
    this.elemento = {};
    this.modo = 'add';
  }
  public edit(key: any): void {
    this.dao.get(key).subscribe({
      next: data => {
        this.elemento = data;
        this.idOriginal = key;
        this.modo = 'edit';
      },
      error: err => this.notify.add(err.message)
    });
  }
  public view(key: any): void {
    this.dao.get(key).subscribe({
      next: data => {
        this.elemento = data;
        this.modo = 'view';
      },
      error: err => this.notify.add(err.message)
    });
  }
  public delete(key: any): void {
    if (!window.confirm('¿Seguro?')) { return; }

    this.dao.remove(key).subscribe({
      next: data => this.load(), // this.list(),
      error: err => this.notify.add(err.message)
    });
  }
    public cancel(): void {
      this.elemento = {};
      this.idOriginal = null;
      // this.list();
      // this.load(this.page)
      // this.router.navigateByUrl(this.listURL);
      this.navigation.back()
    }

    public send(): void {
      switch (this.modo) {
        case 'add':
          this.dao.add(this.elemento).subscribe({
            next: data => this.cancel(),
            error: err => this.notify.add(err.message)
          });
          break;
        case 'edit':
          this.dao.change(this.idOriginal, this.elemento).subscribe({
            next: data => this.cancel(),
            error: err => this.notify.add(err.message)
          });
          break;
        case 'view':
          this.cancel();
          break;
      }
    }

    clear() {
      this.elemento = {};
      this.idOriginal = null;
      this.listado = [];
    }




    page = 0;
    totalPages = 0;
    totalRows = 0;
    rowsPerPage = 8;
    tipo: string = '';
    load(page: number = -1) {
      if(page < 0) page = this.page
      this.dao.page(page, this.rowsPerPage, this.tipo).subscribe({
        next: rslt => {
          this.page = rslt.page;
          this.totalPages = rslt.pages;
          this.totalRows = rslt.rows;
          this.listado = rslt.list;
          this.modo = 'list';
        },
        error: err => this.notify.add(err.message)
      })
    }
  }



