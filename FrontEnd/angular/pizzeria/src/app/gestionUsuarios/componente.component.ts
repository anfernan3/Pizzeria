import { Component, OnDestroy, OnInit} from '@angular/core';
import { ActivatedRoute, Router, ParamMap } from '@angular/router';
import { gestionUsuariosViewModelService } from './servicios.service';

@Component({
  selector: 'app-gestionUsuarios',
  templateUrl: './tmpl-anfitrion.component.html',
  // providers: [ gestionUsuariosViewModelService ],
  styleUrls: ['./componente.component.css']
})
export class gestionUsuariosComponent implements OnInit, OnDestroy {
  constructor(protected vm: gestionUsuariosViewModelService) { }
  public get VM(): gestionUsuariosViewModelService { return this.vm; }
  ngOnInit(): void {
    //this.vm.list();
    this.vm.load();
  }
  ngOnDestroy(): void {
    this.vm.clear()
  }
}
@Component({
  selector: 'app-gestionUsuarios-list',
  templateUrl: './tmpl-list.component.html',
  styleUrls: ['./componente.component.css']
})
export class gestionUsuariosListComponent implements OnInit {
  constructor(protected vm: gestionUsuariosViewModelService) { }
  public get VM(): gestionUsuariosViewModelService { return this.vm; }
  ngOnInit(): void {
    //this.vm.list();
    this.vm.load();
  }
}


@Component({
  selector: 'app-gestionUsuarios-edit',
  templateUrl: './tmpl-form.component.html',
  styleUrls: ['./componente.component.css']
})
export class gestionUsuariosEditComponent implements OnInit, OnDestroy {
  private obs$: any;
  constructor(protected vm: gestionUsuariosViewModelService,
    protected route: ActivatedRoute, protected router: Router) { }
  public get VM(): gestionUsuariosViewModelService { return this.vm; }
  ngOnInit(): void {
    this.obs$ = this.route.paramMap.subscribe(
      (params: ParamMap) => {
        const username = params?.get('username');
        if (username) {
          this.vm.edit(username);
        } else {
          this.router.navigate(['/404.html']);
        }
      });
  }
  ngOnDestroy(): void {
    this.obs$.unsubscribe();
  }
}


export const GESTIONUSURIOS_COMPONENTES = [
  gestionUsuariosComponent, gestionUsuariosListComponent, gestionUsuariosEditComponent
];
