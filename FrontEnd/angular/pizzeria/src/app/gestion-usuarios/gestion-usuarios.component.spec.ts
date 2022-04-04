import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GestionUsuariosComponent } from './gestion-usuarios.component';

describe('GestionUsuariosComponent', () => {
  let component: GestionUsuariosComponent;
  let fixture: ComponentFixture<GestionUsuariosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GestionUsuariosComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GestionUsuariosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
