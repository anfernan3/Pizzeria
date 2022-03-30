import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MenuComponent } from './menu.component';
import { FormsModule } from '@angular/forms';
import { MenubarModule } from 'primeng/menubar';
import {ButtonModule} from 'primeng/button';



@NgModule({
  declarations: [
    MenuComponent
  ],
  exports: [
    MenuComponent
  ],
  imports: [
    CommonModule, FormsModule, MenubarModule, ButtonModule,
  ]
})
export class MenuModule { }
