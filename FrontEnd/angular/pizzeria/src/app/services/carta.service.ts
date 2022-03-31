import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Observable, observable } from "rxjs";
import { Pizzas } from "../modelos/pizzas";
import { Global } from "./url";


@Injectable() // Para indicar que esto es un servicio

export class CartaService {

  public url: string;
  constructor(private _http: HttpClient){
    this.url = Global.url
  }

  pruebas(){
    return "Soy el servicio de pizzas!!!";
  }

  getPizzas():Observable<any>{
    return this._http.get(this.url+ 'pizzas');
  }

}
