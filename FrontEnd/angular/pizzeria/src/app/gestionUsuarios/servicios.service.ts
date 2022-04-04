import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { LoggerService } from 'src/lib/my-core';
import { RESTDAOSecurityService } from '../base-code/RESTDAOSecurityService';
import { ModoCRUD } from '../base-code/tipos';
import { NavigationService, NotificationService } from '../common-services';
import { AuthService, AUTH_REQUIRED } from '../security';

export class Usuario {
  username: string | null = null;
  roles: string | null = null;
}


@Injectable({
  providedIn: 'root'
})
export class gestionUsuariosDAOService extends RESTDAOSecurityService<Usuario, any> {
  constructor(http: HttpClient) {
    super(http, 'usuarios', { context: new HttpContext().set(AUTH_REQUIRED, true) });
  }
  override query(tipo: string = ''): Observable<Array<Usuario>> {
    return this.http.get<Array<Usuario>>(`${this.securityUrl + tipo}`, this.option);
  }

  page(page: number, rows: number = 20, tipo: string = ''): Observable<{ page: number, pages: number, rows: number, list: Array<any> }> {
    return new Observable(subscriber => {
      this.http.get<any>(`${this.securityUrl + tipo}?page=${page}&size=${rows}&sort=nombre`, this.option)
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
export class gestionUsuariosViewModelService {
  protected modo: ModoCRUD = 'list';
  protected listado: Array<any> = [];
  protected elemento: any = {};
  protected idOriginal: any = null;
  protected listURL = '/usuarios';

  constructor(protected notify: NotificationService, protected out: LoggerService, protected dao: gestionUsuariosDAOService,
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
