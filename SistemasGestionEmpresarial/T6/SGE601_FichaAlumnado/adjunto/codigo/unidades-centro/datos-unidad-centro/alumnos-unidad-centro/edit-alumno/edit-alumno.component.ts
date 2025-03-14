import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AlumnosService } from 'src/app/services/alumnos.service';
import { CLOSE } from 'src/app/shared/messages';

@Component({
	selector: 'app-edit-alumno',
	templateUrl: './edit-alumno.component.html',
})
export class EditAlumnoComponent implements OnInit {
	alumnoForm: FormGroup;

	constructor(
		private fb: FormBuilder,
		private alumnosService: AlumnosService,
		public dialogRef: MatDialogRef<EditAlumnoComponent>,
		@Inject(MAT_DIALOG_DATA) public data: any,
		public snackBar: MatSnackBar

	) { }

	ngOnInit(): void {
		this.alumnoForm = this.fb.group({
			id: [this.data.alumno.id, Validators.required],
			nombre: [this.data.alumno.nombre, Validators.required],
			apellidos: [this.data.alumno.apellidos, Validators.required],
			fecha_nacimiento: [this.data.alumno.fecha_nacimiento, Validators.required],
			linkedin: [this.data.alumno.linkedin],
			nivel_ingles: [this.data.alumno.nivel_ingles],
			minusvalia: [this.data.alumno.minusvalia],
			otra_formacion: [this.data.alumno.otra_formacion],
			id_unidad_centro: [{ value: this.data.alumno.id_unidad_centro, disabled: true }, Validators.required]
		});
	}

	save() {
		if (this.alumnoForm.valid) {
			const alumnoActualizado = this.alumnoForm.getRawValue();
			this.alumnosService.editAlumno(alumnoActualizado).subscribe(RESPONSE => {
				if (RESPONSE.status) {
					this.snackBar.open(RESPONSE.message, CLOSE, { duration: 5000 });
					this.dialogRef.close({ ok: RESPONSE.status, data: RESPONSE.data });
				}
				else {
					this.snackBar.open(RESPONSE.message, CLOSE, { duration: 5000 });
				}
			});
		}
	}

	async confirmEdit() {
		if (this.alumnoForm.valid) {
			const alumnoActualizado = this.alumnoForm.getRawValue();
			this.alumnosService.editAlumno(alumnoActualizado).subscribe(RESPONSE => {
				if (RESPONSE.status) {
					this.snackBar.open(RESPONSE.message, CLOSE, { duration: 5000 });
					this.dialogRef.close({ ok: RESPONSE.status, data: RESPONSE.data });
				}
				else {
					this.snackBar.open(RESPONSE.message, CLOSE, { duration: 5000 });
				}
			});
		}

	}

	onNoClick(): void {
		this.dialogRef.close();
	}
}
