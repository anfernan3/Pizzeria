import { HttpResponse } from '@angular/common/http';
import { Component, Input } from '@angular/core';

import { MenuItem } from 'primeng/api';
import { AuthService, LoginService } from '../security';
@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css'],
})
export class MenuComponent {

  constructor(public auth: AuthService){

  }

  items: MenuItem[] = [];

  ngOnInit() {
    this.items = [
      {
        label: 'Carta',
        icon: 'pi pi-fw pi-book',
        routerLink: '/carta'
      },
      {
        label: 'Nuestros Chef',
        icon: 'pi pi-fw pi-user',
        routerLink: '/chef'
      },
      {
        label: 'Promociones',
        icon: 'pi pi-fw pi-wallet',
        routerLink: '/promociones'
      },

      {
        label: 'Ingredientes',
        icon: 'pi pi-fw pi-box',
        visible: this.auth.isInRoles('GERENTE', 'TIENDA'),
        routerLink: '/ingredientes'

      },
      {
        label: 'Comentarios',
        icon: 'pi pi-fw pi-comments',
        routerLink: '/comentarios',
        visible: this.auth.isInRoles('USUARIO', 'GERENTE')
      },
      {
        label: 'Pedidos',
        icon: 'pi pi-fw pi-euro',
        routerLink: '/pedidos',
        visible: this.auth.isInRoles('GERENTE', 'TIENDA')
      },
      {
        label: 'Gestion de usuarios',
        icon: 'pi pi-fw pi-user',
        routerLink: '/gestionUsuarios',
        visible: this.auth.isInRoles('GERENTE')
      },
    ];
  }
}
