import { Component, OnInit } from '@angular/core';
import {
  FormGroup,
  FormControl,
  Validators,
  FormArray,
  ValidatorFn,
  AbstractControl,
  ValidationErrors,
} from '@angular/forms';
import { User, RegisterUserDAO, LoginService } from '../security.service';
import { Router } from '@angular/router';
import { NotificationService, NotificationType } from 'src/app/common-services';
import { LoggerService } from 'src/lib/my-core';

@Component({
  selector: 'app-register-user',
  templateUrl: './register-user.component.html',
  styleUrls: ['./register-user.component.css'],
})
export class RegisterUserComponent implements OnInit {
  public miForm: FormGroup = new FormGroup({});
  private model: User = new User();

  constructor(
    private dao: RegisterUserDAO,
    private notify: NotificationService,
    private out: LoggerService,
    private router: Router,
    private login: LoginService
  ) {}

  passwordMatchValidator(): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null =>
      control?.get('passwordValue')?.value ===
      control?.get('passwordConfirm')?.value
        ? null
        : { mismatch: 'Son distintos' };
  }

  ngOnInit() {
    // const fa = new FormArray([]);
    // this.model.roles.forEach(r => fa.push(
    //   new FormGroup({ role: new FormControl(r.role , Validators.required) })
    // ));
    this.miForm = new FormGroup({
      username: new FormControl('', [
        Validators.required,
        Validators.minLength(2),
        Validators.maxLength(100),
        Validators.email,
      ]),
      nombre: new FormControl('', [
        Validators.required,
        Validators.minLength(2),
        Validators.maxLength(50),
      ]),
      apellido1: new FormControl('', [
        Validators.required,
        Validators.minLength(2),
        Validators.maxLength(50),
      ]),
      apellido2: new FormControl('', [
        Validators.required,
        Validators.minLength(2),
        Validators.maxLength(50),
      ]),
      telefono: new FormControl('', [
        Validators.required,
        Validators.minLength(9),
        Validators.maxLength(30),
      ]),
      password: new FormGroup(
        {
          passwordValue: new FormControl('', [
            Validators.required,
            Validators.minLength(8),
            Validators.pattern(
              /^(?=.*\d)(?=.*[\u0021-\u002b\u003c-\u0040])(?=.*[A-Z])(?=.*[a-z])\S{8,16}$/
            ),
          ]),
          passwordConfirm: new FormControl('', Validators.minLength(2)),
        },
        this.passwordMatchValidator()
      ),
    });
    // for (const name in this.miForm.controls) {
    //   if (this.miForm.controls[name] instanceof FormControl) {
    //     this.miForm.controls[name].valueChanges.subscribe(
    //       data => { this.formatErrorMessage(this.miForm.controls[name] as FormControl); }
    //     );
    //     // this.formatErrorMessage(this.miForm.controls[name] as FormControl);
    //     this.miForm.controls[name].setValue(this.miForm.controls[name].value)
    //   }
    // }
  }
  public getErrorMessage(name: string): string {
    let cntr = this.miForm.get(name);
    let msg = '';
    if (cntr)
      for (let err in cntr.errors) {
        switch (err) {
          case 'required':
            msg += 'Es obligatorio. ';
            break;
          case 'minlength':
            msg += `Como mínimo debe tener ${cntr.errors[err].requiredLength} caracteres. `;
            break;
          case 'maxlength':
            msg += `Como máximo debe tener ${cntr.errors[err].requiredLength} caracteres. `;
            break;
          case 'pattern':
          case 'email':
            msg += 'El formato no es correcto. ';
            break;
          case 'min':
            msg += `El valor debe ser mayor o igual a ${cntr.errors[err].min}. `;
            break;
          case 'max':
            msg += `El valor debe ser inferior o igual a ${cntr.errors[err].max}. `;
            break;
          case 'telefono':
            msg += `El valor debe ser igual a ${cntr.errors[err].max}. `;
            break;
          default:
            if (typeof cntr.errors[err] === 'string')
              msg += `${cntr.errors[err]}${
                cntr.errors[err].endsWith('.') ? '' : '.'
              } `;
            else if (typeof cntr.errors[err]?.message === 'string')
              msg += `${cntr.errors[err].message}${
                cntr.errors[err].message.endsWith('.') ? '' : '.'
              } `;
            break;
        }
      }
    return msg.trim();
  }
  private formatErrorMessage(cntr: FormControl): void {
    let msg = '';
    for (let err in cntr.errors) {
      switch (err) {
        case 'required':
          msg += 'Es obligatorio. ';
          break;
        case 'minlength':
          msg += `Como mínimo debe tener ${cntr.errors[err].requiredLength} caracteres. `;
          break;
        case 'maxlength':
          msg += `Como máximo debe tener ${cntr.errors[err].requiredLength} caracteres. `;
          break;
        case 'pattern':
        case 'email':
          msg += 'El formato no es correcto. ';
          break;
        case 'min':
          msg += `El valor debe ser mayor o igual a ${cntr.errors[err].min}. `;
          break;
        case 'max':
          msg += `El valor debe ser inferior o igual a ${cntr.errors[err].max}. `;
          break;
        case 'telefono':
          msg += `El valor debe ser igual a ${cntr.errors[err].max}. `;
          break;
        default:
          if (typeof cntr.errors[err] === 'string')
            msg += `${cntr.errors[err]}${
              cntr.errors[err].endsWith('.') ? '' : '.'
            } `;
          else if (typeof cntr.errors[err]?.message === 'string')
            msg += `${cntr.errors[err].message}${
              cntr.errors[err].message.endsWith('.') ? '' : '.'
            } `;
          break;
      }
    }
    cntr.setErrors(Object.assign({}, cntr.errors, { customMsg: msg }));
  }
  addRole(): void {
    (this.miForm.get('roles') as FormArray).push(
      new FormGroup({ role: new FormControl('Usuarios', Validators.required) })
    );
  }
  deleteRole(ind: number): void {
    (this.miForm.get('roles') as FormArray).removeAt(ind);
  }
  send(): void {
    const data = this.miForm.value;
    this.model = {
      username: data.username,
      nombre: data.nombre,
      apellido1: data.apellido1,
      apellido2: data.apellido2,
      telefono: data.telefono,
      password: data.password.passwordValue,
    } as User;
    this.dao.add(this.model).subscribe(
      (rslt) => {
        this.login.login(data.username, data.password.passwordValue).subscribe(
          (datos) => {
            if (datos) {
              this.notify.add('Usuario registrado', NotificationType.log);
              this.router.navigateByUrl('/');
            } else {
              this.notify.add('Error en el registro.');
            }
          },
          (err) => {
            this.notify.add(err.message);
          }
        );
      },
      (err) => {
        this.notify.add(err.message);
      }
    );
  }
}
