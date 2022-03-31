import { inject } from '@angular/core';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router, ParamMap } from '@angular/router';
import { ComentariosViewModelService } from './servicios.service';



@Component({
  selector: 'app-comentarios',
  templateUrl: './tmpl-anfitrion.component.html',
  styleUrls: ['./componente.component.css']
 })
 export class ComentariosComponent implements OnInit {
  constructor(protected vm: ComentariosViewModelService) { }
  public get VM(): ComentariosViewModelService { return this.vm; }
  ngOnInit(): void {
  this.vm.list();
  }
 }

 @Component({
  selector: 'app-comentarios-list',
  templateUrl: './tmpl-list.component.html',
  styleUrls: ['./componente.component.css']
 })
 export class ComentariosListComponent implements OnInit {
  constructor(protected vm: ComentariosViewModelService) { }
  public get VM(): ComentariosViewModelService { return this.vm; }
  ngOnInit(): void { }
 }


@Component({
 selector: 'app-comentarios-add',
 templateUrl: './tmpl-form.component.html',
 styleUrls: ['./componente.component.css']
})
export class ComentariosAddComponent implements OnInit {
 constructor(protected vm: ComentariosViewModelService) { }
 public get VM(): ComentariosViewModelService { return this.vm; }
 ngOnInit(): void { }
}

@Component({
  selector: 'app-comentarios-edit',
  templateUrl: './tmpl-form.component.html',
  styleUrls: ['./componente.component.css']
 })
 export class ComentariosEditComponent implements OnInit, OnDestroy {
  constructor(protected vm: ComentariosViewModelService) { }
  public get VM(): ComentariosViewModelService { return this.vm; }
  ngOnInit(): void { }
  ngOnDestroy(): void { }
 }

 @Component({
  selector: 'app-comentarios-view',
  templateUrl: './tmpl-view.component.html',
  styleUrls: ['./componente.component.css']
 })
 export class ComentariosViewComponent implements OnInit, OnDestroy {
  constructor(protected vm: ComentariosViewModelService) { }
  public get VM(): ComentariosViewModelService { return this.vm; }
  ngOnInit(): void { }
  ngOnDestroy(): void { }
 }

 export const COMENTARIOS_COMPONENTES = [
  ComentariosComponent, ComentariosListComponent, ComentariosAddComponent,
  ComentariosEditComponent, ComentariosViewComponent,
 ];
