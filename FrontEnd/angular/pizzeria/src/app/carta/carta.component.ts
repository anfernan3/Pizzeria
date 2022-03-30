import { Component, OnInit } from '@angular/core';
import { SelectItem } from 'primeng/api';
import { CartaService } from '../services/carta.service';

@Component({
  selector: 'app-carta',
  templateUrl: './carta.component.html',
  styleUrls: ['./carta.component.css'],
  providers: [CartaService]
})

export class CartaComponent implements OnInit {

    ngOnInit() {

    }



}
