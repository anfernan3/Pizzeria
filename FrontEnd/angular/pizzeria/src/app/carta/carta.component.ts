import { Component, OnInit } from '@angular/core';
import { CartaService } from '../services/carta.service';

@Component({
  selector: 'app-carta',
  templateUrl: './carta.component.html',
  styleUrls: ['./carta.component.css'],
  providers: [CartaService]
})
export class CartaComponent implements OnInit {

  holamundo(){
    return ("Hola Mundo desde el servicio carta")
  }

  constructor(private _cartaService: CartaService // el guión bajo para vinculación con un servicio) {

  ){}


  ngOnInit(): void {
    console.log(this._cartaService);
  }

}
