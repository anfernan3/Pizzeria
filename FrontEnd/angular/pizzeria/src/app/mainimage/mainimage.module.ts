import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MainimageComponent } from './mainimage.component';



@NgModule({
  declarations: [
    MainimageComponent,
  ],
  exports: [
    MainimageComponent,
  ],
  imports: [
    CommonModule
  ]
})
export class MainimageModule { }
