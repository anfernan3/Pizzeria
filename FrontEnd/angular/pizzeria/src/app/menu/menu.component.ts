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
        //url: '../carta/carta.component.html'
      },
      {
        label: 'Nuestros Chef',
        icon: 'pi pi-fw pi-user',
        //url: '../chef/chef.component.html'
      },
      {
        label: 'Promociones',
        icon: 'pi pi-fw pi-wallet',
        //url: '../promociones/promociones.component.html'
      }
    ];
  }
}
