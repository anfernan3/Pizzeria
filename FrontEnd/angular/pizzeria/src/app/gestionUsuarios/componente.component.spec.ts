import { HttpClientTestingModule } from '@angular/common/http/testing';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { LoggerService } from 'src/lib/my-core';
import { NotificationService } from '../common-services';

import { IngredientesComponent } from './componente.component';

describe('IngredientesComponent', () => {
  let component: IngredientesComponent;
  let fixture: ComponentFixture<IngredientesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ IngredientesComponent ],
      providers: [ NotificationService, LoggerService ],
      imports: [ HttpClientTestingModule, RouterTestingModule, FormsModule ],
      schemas: [ NO_ERRORS_SCHEMA ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(IngredientesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
