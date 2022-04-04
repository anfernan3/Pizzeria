import { HttpResponse } from '@angular/common/http';
import { Component, Input, OnChanges, SimpleChanges } from '@angular/core';

import { MenuItem } from 'primeng/api';
import { AuthService, LoginService } from '../security';
@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css'],
})
export class MenuComponent implements OnChanges {
  constructor(public auth: AuthService) {}
  ngOnChanges(changes: SimpleChanges): void {
    this.ngOnInit();
  }

  items: MenuItem[] = [];

  ngOnInit() {
    this.auth.Notificacion.subscribe(
      (data) =>
        (this.items = [
          {
            label: 'Carta',
            icon: 'pi pi-fw pi-book',
            routerLink: '/carta',
          },
          {
            label: 'Chef',
            icon: 'pi pi-fw pi-user',
            routerLink: '/chef',
          },
          {
            label: 'ofertas',
            icon: 'pi pi-fw pi-wallet',
            routerLink: '/ofertas',
          },

          {
            label: 'Ingredientes',
            icon: 'pi pi-fw pi-box',
            visible: this.auth.isInRoles('GERENTE', 'TIENDA'),
            routerLink: '/ingredientes',
          },
          {
            label: 'Comentarios',
            icon: 'pi pi-fw pi-comments',
            routerLink: '/comentarios',
            visible: this.auth.isInRoles('USUARIO', 'GERENTE'),
          },
          {
            label: 'Pedidos',
            icon: 'pi pi-fw pi-euro',
            routerLink: '/pedidos',
            visible: this.auth.isInRoles('GERENTE', 'TIENDA'),
          },
          {
            label: 'Gestion de usuarios',
            icon: 'pi pi-fw pi-user',
            routerLink: '/gestionUsuarios',
            visible: this.auth.isInRoles('GERENTE'),
          },
        ])
    );
  }
}
