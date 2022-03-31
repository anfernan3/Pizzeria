import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { LoggerService } from 'src/lib/my-core';
import { NotificationService } from '../common-services/notification.service';
import { AuthService } from '../security';
import { RESTDAOService, AUTH_REQUIRED } from './RESTDAOService';

export type ModoCRUD = 'list' | 'add' | 'edit' | 'view' | 'delete';

export class Comentarios {
  id: number = 0;
  comentario: string | null = null;
  fecha: string | null = null;
  id_usuario: number | null = null;
  id_pizza: number | null = null;
}


@Injectable({
  providedIn: 'root'
 })
 export class ComentariosDAOService extends RESTDAOService<any, any> {
  constructor(http: HttpClient) {
  super(http, 'comentarios', {
  context: new HttpContext().set(AUTH_REQUIRED, true)
  });
  }
 }

@Injectable({
  providedIn: 'root',
})
export class ComentariosViewModelService {
  protected modo: ModoCRUD = 'list';
  protected listado: Array<any> = [];
  protected elemento: any = {};
  protected idOriginal: any = null;
  protected listURL = '/comentarios';

  constructor(
    protected notify: NotificationService,
    protected out: LoggerService,
    protected dao: ComentariosDAOService,
    protected router: Router,
    protected auth: AuthService,
  ) {}

  public get Modo(): ModoCRUD { return this.modo; }
  public get Listado(): Array<any> { return this.listado; }
  public get Elemento(): any { return this.elemento; }

  public list(): void {
    this.dao.query().subscribe({
      next: (data) => {
        this.listado = data;
        this.modo = 'list';
      },
      error: (err) => this.notify.add(err.message),
    });
  }
  public add(): void {
    this.elemento = { id_usuario: this.auth.Name};
    this.modo = 'add';
  }
  public edit(key: any): void {
    this.dao.get(key).subscribe({
      next: (data) => {
        this.elemento = data;
        this.idOriginal = key;
        this.modo = 'edit';
      },
      error: (err) => this.notify.add(err.message),
    });
  }
  public view(key: any): void {
    this.dao.get(key).subscribe({
      next: (data) => {
        this.elemento = data;
        this.modo = 'view';
      },
      error: (err) => this.notify.add(err.message),
    });
  }
  public delete(key: any): void {
    if (!window.confirm('¿Seguro?')) {
      return;
    }
    this.dao.remove(key).subscribe({
      next: (data) => this.list(),
      error: (err) => this.notify.add(err.message),
    });
  }

  clear() {
    this.elemento = {};
    this.idOriginal = null;
    this.listado = [];
  }

  public cancel(): void {
    this.elemento = {};
    this.idOriginal = null;
    this.list();
    // this.router.navigateByUrl(this.listURL);
  }

  public send(): void {
    switch (this.modo) {
      case 'add':
        this.dao.add(this.elemento).subscribe({
          next: (data) => this.cancel(),
          error: (err) => this.notify.add(err.message),
        });
        break;
      case 'edit':
        this.dao.change(this.idOriginal, this.elemento).subscribe({
          next: (data) => this.cancel(),
          error: (err) => this.notify.add(err.message),
        });
        break;
      case 'view':
        this.cancel();
        break;
    }
  }
}
