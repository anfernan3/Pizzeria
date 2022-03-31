import { Component, OnInit } from '@angular/core';
import { SelectItem } from 'primeng/api';
import { CartaService } from '../services/carta.service';

@Component({
  selector: 'app-carta',
  templateUrl: './carta.component.html',
  styleUrls: ['./carta.component.css'],
  providers: [CartaService],
})
export class CartaComponent implements OnInit {
  constructor(private _cartaService: CartaService) {}

  ngOnInit() {
    // subscribe permite recoger los datos que devuelve la petición http
    this._cartaService.getPizzas().subscribe({
      next: (response) => {
        console.log(response);
      },
      error: (error) => {
        console.log(error);
      },
    });
  }
}
