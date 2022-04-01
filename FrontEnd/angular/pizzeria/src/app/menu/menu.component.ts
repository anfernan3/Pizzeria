import { Component, Input } from '@angular/core';

import { MenuItem } from 'primeng/api';
@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css'],
})
export class MenuComponent {

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
        icon: 'pi pi-fw pi-book',
        routerLink: '/ingredientes'
      },
      {
        label: 'Comentarios',
        icon: 'pi pi-fw pi-book',
        routerLink: '/comentarios'
      },
    ];
  }
}
