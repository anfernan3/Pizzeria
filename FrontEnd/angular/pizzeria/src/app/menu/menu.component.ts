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
      },
      {
        label: 'Nuestros Chef',
        icon: 'pi pi-fw pi-user',
      },
      {
        label: 'Promociones',
        icon: 'pi pi-fw pi-wallet',
      }
    ];
  }
}
