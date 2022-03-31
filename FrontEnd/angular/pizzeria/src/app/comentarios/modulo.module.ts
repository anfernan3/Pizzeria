import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { MyCoreModule } from 'src/lib/my-core';
import { COMENTARIOS_COMPONENTES } from '../comentarios/componente.component';
import { CommonServicesModule } from '../common-services';
import { CommonComponentModule } from '../common-component/common-component.module';



@NgModule({
  declarations: [ COMENTARIOS_COMPONENTES ],
  imports: [
    CommonModule, FormsModule, RouterModule.forChild([]),
    MyCoreModule, CommonServicesModule, CommonComponentModule,
  ],
  exports: [ COMENTARIOS_COMPONENTES ]
})
export class ComentariosModule { }
