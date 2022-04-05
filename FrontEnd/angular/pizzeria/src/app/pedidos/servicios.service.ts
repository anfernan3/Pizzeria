import { HttpClient, HttpContext } from '@angular/common/http';
import { emitDistinctChangesOnlyDefaultValue } from '@angular/compiler';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { LoggerService } from 'src/lib/my-core';
import { RESTDAOService } from '../base-code/RESTDAOService';
import { ModoCRUD } from '../base-code/tipos';
import { NavigationService, NotificationService } from '../common-services';
import { AuthService, AUTH_REQUIRED } from '../security';
import {Carrito} from '../carrito/servicios.service'
export class Pedido {
  idPedido: number = 0;
  entregadoPor: string | null = null;
  estado: string | null = null;
  fecha: Date | null = null;
  fechaEntrega: Date | null = null;
  importe: number = 0;
  preparadoPor: string | null = null;
  direccion: string | null = null;
  usuario: string | null = null;

}

@Injectable({
  providedIn: 'root'
})
export class PedidosDAOService extends RESTDAOService<any, any> {
  constructor(http: HttpClient) {
    super(http, 'pedidos', { context: new HttpContext().set(AUTH_REQUIRED, true) });
  }
  override query(estado: string = ''): Observable<Array<Pedido>> {
    return this.http.get<Array<Pedido>>(`${this.baseUrl + estado}`, this.option);
  }



  page(page: number, rows: number = 20, estado: string = ''): Observable<{ page: number, pages: number, rows: number, list: Array<any> }> {
    return new Observable(subscriber => {
      this.http.get<any>(`${this.baseUrl + estado}?page=${page}&size=${rows}&sort=nombre`, this.option)
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
export class PedidosViewModelService {
  protected modo: ModoCRUD = 'list';
  protected listado: Array<any> = [];
  protected elemento: any = {};
  protected idOriginal: any = null;
  protected listURL = '/pedidos';

  constructor(protected notify: NotificationService, protected out: LoggerService, protected dao: PedidosDAOService,
    public auth: AuthService, protected router: Router, private navigation: NavigationService) { }

  public get Modo(): ModoCRUD { return this.modo; }
  public get Listado(): Array<any> { return this.listado; }
  public get Elemento(): any { return this.elemento; }
  public set Elemento(value: any) { this.elemento = value; }
  public get isAutenticated(): boolean { return this.auth.isAutenticated; }


  public list(): void {
    this.dao.query(this.estado).subscribe({
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
  estado: string = '';
  load(page: number = -1) {
    if(page < 0) page = this.page
    this.dao.page(page, this.rowsPerPage, this.estado).subscribe({
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
