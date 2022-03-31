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
import {CardModule} from 'primeng/card';
import { CommonServicesModule } from './common-services';
import { CommonComponentModule } from './common-component';
import { SecurityModule } from './security';
import { LoggerService, MyCoreModule } from 'src/lib/my-core';
import { ComentariosComponent } from './comentarios/comentarios.component';

@NgModule({
  declarations: [
    AppComponent,
    PromocionesComponent,
    ErrorComponent,
    ComentariosComponent,
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
    CardModule,
    CommonServicesModule,
    CommonComponentModule,
    SecurityModule,
    MyCoreModule,
  ],

  providers: [LoggerService],
  bootstrap: [AppComponent]
})
export class AppModule { }
