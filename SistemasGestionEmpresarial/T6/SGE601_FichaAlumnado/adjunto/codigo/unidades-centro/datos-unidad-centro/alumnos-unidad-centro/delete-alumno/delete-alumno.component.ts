import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Alumno } from 'src/app/shared/interfaces/alumno';
import { AlumnosService } from 'src/app/services/alumnos.service';
import { CLOSE } from 'src/app/shared/messages';

@Component({
	selector: 'app-delete-alumno',
	templateUrl: './delete-alumno.component.html',
	styleUrls: ['./delete-alumno.component.scss']
})
export class DeleteAlumnoComponent implements OnInit {

	ENTIDAD: String;

	constructor(
		public dialogRef: MatDialogRef<DeleteAlumnoComponent>,
		@Inject(MAT_DIALOG_DATA) public alumno: Alumno,
		public servicioAlumnos: AlumnosService,
		public snackBar: MatSnackBar,
	) { }

	ngOnInit(): void {
	}

	onNoClick(): void {
		this.dialogRef.close({ ok: false });
	}

	async confirmDelete() {
		const RESPONSE = await this.servicioAlumnos.deleteAlumno(this.alumno.id).toPromise();
		if (RESPONSE.status) {
			this.snackBar.open(RESPONSE.message, CLOSE, { duration: 5000 });
			this.dialogRef.close({ ok: RESPONSE.status, data: RESPONSE.data });
		} else { this.snackBar.open(RESPONSE.message, CLOSE, { duration: 5000 }); }
	}

}
