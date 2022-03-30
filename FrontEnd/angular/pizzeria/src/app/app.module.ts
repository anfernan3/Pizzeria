import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ChefComponent } from './chef/chef.component';
import {ChefModule} from './chef/chef.module';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule, ChefModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
