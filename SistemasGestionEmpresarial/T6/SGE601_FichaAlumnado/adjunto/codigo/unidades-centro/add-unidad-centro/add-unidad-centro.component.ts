import { Component, Inject, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UnidadCentro } from 'src/app/shared/interfaces/unidad-centro';
import { UnidadesCentroService } from 'src/app/services/unidades-centro.service';
import { CLOSE, ENTIDAD_UNIDAD_CENTRO, INVALID_FORM } from 'src/app/shared/messages';
import { Ciclo } from 'src/app/shared/interfaces/ciclo';
import { CiclosService } from 'src/app/services/ciclos.service';

@Component({
	selector: 'app-add-unidad-centro',
	templateUrl: './add-unidad-centro.component.html',
	styleUrls: ['./add-unidad-centro.component.scss']
})
export class AddUnidadCentroComponent implements OnInit {
	unidadCentroForm: FormGroup;
	ciclos: Ciclo[];

	ENTIDAD: String;

	constructor(public dialogRef: MatDialogRef<AddUnidadCentroComponent>,
		private snackBar: MatSnackBar,
		private servicioUnidadesCentro: UnidadesCentroService,
		private servicioCiclos: CiclosService,
		@Inject(MAT_DIALOG_DATA) public idEntidad: number,

	) { }

	ngOnInit(): void {
		this.unidadCentroForm = new FormGroup({
			id_ciclo: new FormControl(null, Validators.required),
			unidad_centro: new FormControl(null, Validators.required),
			observaciones: new FormControl(null)
		  });
		  
		this.ENTIDAD = ENTIDAD_UNIDAD_CENTRO;

		this.getCiclos();

	}

	async confirmAdd() {
		if (this.unidadCentroForm.valid) {
		  const unidadCentro = this.unidadCentroForm.value as UnidadCentro;
		  
		  console.log("Enviando al backend:", unidadCentro);
	  
		  const RESPONSE = await this.servicioUnidadesCentro.addUnidadCentro(unidadCentro).toPromise();
		  console.log("Respuesta del backend:", RESPONSE);
	  
		  if (RESPONSE.ok) {
			this.snackBar.open(RESPONSE.message, CLOSE, { duration: 5000 });
			this.dialogRef.close({ ok: RESPONSE.ok, data: RESPONSE.data });
		  } else {
			this.snackBar.open(RESPONSE.message, CLOSE, { duration: 5000 });
		  }
		} else {
		  this.snackBar.open(INVALID_FORM, CLOSE, { duration: 5000 });
		}
	  }
	  

	async getCiclos() {
		const RESPONSE = await this.servicioCiclos.getAllCiclos().toPromise();
		if (RESPONSE.ok) {
			this.ciclos = RESPONSE.data as Ciclo[];
		}
	}

	onNoClick() {
		this.dialogRef.close({ ok: false });
	}
}
