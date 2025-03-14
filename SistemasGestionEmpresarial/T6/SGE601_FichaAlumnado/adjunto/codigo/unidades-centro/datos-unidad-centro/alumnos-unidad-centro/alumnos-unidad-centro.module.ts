import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AlumnosUnidadCentroComponent } from './alumnos-unidad-centro.component';
import { AddAlumnoComponent } from './add-alumno/add-alumno.component';
import { EditAlumnoComponent } from './edit-alumno/edit-alumno.component';
import { EdadPipe } from '../../../shared/pipes/edad.pipe';
import { CrudMaterialModule } from 'src/app/modules/crud-material/crud-material.module';
import { AlumnosUnidadCentroRoutingModule } from './alumnos-unidad-centro-routing.module';
import { DeleteAlumnoComponent } from './delete-alumno/delete-alumno.component';

@NgModule({
  declarations: [
    AlumnosUnidadCentroComponent,
    AddAlumnoComponent,
    EditAlumnoComponent,
    DeleteAlumnoComponent,
    EdadPipe
  ],
  imports: [
    CommonModule,
    CrudMaterialModule,
    AlumnosUnidadCentroRoutingModule
  ]
})
export class AlumnosUnidadCentroModule { }
