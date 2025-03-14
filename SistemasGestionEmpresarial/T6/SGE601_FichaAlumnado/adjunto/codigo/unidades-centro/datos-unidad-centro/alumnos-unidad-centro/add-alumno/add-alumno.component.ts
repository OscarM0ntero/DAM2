import { Component, OnInit, Inject } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AlumnosService } from 'src/app/services/alumnos.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Alumno } from 'src/app/shared/interfaces/alumno';
import { CLOSE, INVALID_FORM } from 'src/app/shared/messages';
import { MatSnackBar } from '@angular/material/snack-bar';
import { NULL_EXPR } from '@angular/compiler/src/output/output_ast';

@Component({
  selector: 'app-add-alumno',
  templateUrl: './add-alumno.component.html'
})
export class AddAlumnoComponent implements OnInit {

  alumnoForm: FormGroup;

  constructor(
    private alumnosService: AlumnosService,
    public dialogRef: MatDialogRef<AddAlumnoComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { id_unidad_centro: number },
    private snackBar: MatSnackBar,

  ) { }

  ngOnInit(): void {
    this.createForm();
  }

  createForm() {
    this.alumnoForm = new FormGroup({
      id: new FormControl('', [Validators.required, Validators.maxLength(20)]),
      nombre: new FormControl('', [Validators.required, Validators.maxLength(50)]),
      apellidos: new FormControl('', [Validators.required]),
      fecha_nacimiento: new FormControl('', Validators.required),
      linkedin: new FormControl('', Validators.pattern('https?://.+')),
      nivel_ingles: new FormControl(''),
      minusvalia: new FormControl(0, [Validators.min(0), Validators.max(100)]),
      otra_formacion: new FormControl(''),
    });
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  async confirmAdd() {
    if (this.alumnoForm.valid) {
      const alumno = this.alumnoForm.value as Alumno;
      alumno.id_unidad_centro = this.data.id_unidad_centro;

      this.alumnosService.addAlumno(alumno).subscribe(RESPONSE => {
        if (RESPONSE.status) {
          this.snackBar.open(RESPONSE.message, CLOSE, { duration: 5000 });
          this.dialogRef.close({ ok: RESPONSE.status, data: RESPONSE.data });
        } else {
          this.snackBar.open(RESPONSE.message, CLOSE, { duration: 5000 });
        }
      });
    }
  }
}
