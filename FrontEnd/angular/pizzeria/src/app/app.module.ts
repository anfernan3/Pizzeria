import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module'
import { AppComponent } from './app.component';

import {ButtonModule} from 'primeng/button';
import {MenubarModule} from 'primeng/menubar';
import { MenuModule } from './menu/menu.module';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { PromocionesComponent } from './promociones/promociones.component';
import { FooterModule } from './footer/footer.module';
import { ErrorComponent } from './error/error.component';
import { MainimageModule } from './mainimage/mainimage.module';
@NgModule({
  declarations: [
    AppComponent,
    PromocionesComponent,
    ErrorComponent,
  ],

  imports:[
    BrowserModule,
    ButtonModule,
    MenubarModule,
    MenuModule,
    FormsModule,
    HttpClientModule,
    FooterModule,
    AppRoutingModule,
    MainimageModule,
  ],

  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
