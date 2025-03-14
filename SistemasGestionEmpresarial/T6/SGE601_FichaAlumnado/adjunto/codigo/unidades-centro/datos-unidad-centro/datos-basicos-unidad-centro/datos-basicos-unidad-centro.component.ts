import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { DatosUnidadCentroComponent } from '../datos-unidad-centro.component';
import { ENTIDAD_UNIDAD_CENTRO } from '../../../shared/messages';
import { UnidadCentro } from 'src/app/shared/interfaces/unidad-centro';
import { UnidadesCentroService } from 'src/app/services/unidades-centro.service';
import { Ciclo } from 'src/app/shared/interfaces/ciclo';
import { CiclosService } from 'src/app/services/ciclos.service';

@Component({
	selector: 'app-datos-basicos-entidad',
	templateUrl: './datos-basicos-unidad-centro.component.html',
	styleUrls: ['./datos-basicos-unidad-centro.component.scss']
})
export class DatosBasicosUnidadCentroComponent implements OnInit {

	unidadCentroForm: FormGroup;
	unidadCentro: UnidadCentro;
	ciclos: Ciclo[];
	unidadCentroNombre: String;

	ENTIDAD: String;

	constructor(
		private datosUnidadCentro: DatosUnidadCentroComponent,
		private unidadCentroService: UnidadesCentroService,
		private servicioCiclos: CiclosService

	) {
		this.ciclos = this.datosUnidadCentro.datosEditarUnidadCentro.ciclos;
	}

	ngOnInit(): void {
		this.ENTIDAD = ENTIDAD_UNIDAD_CENTRO;
		this.setForm();
		this.unidadCentroNombre = this.unidadCentroService.unidadCentro?.unidad_centro;
		this.unidadCentroForm.valueChanges.subscribe(form => {
			this.unidadCentroService.setDatosBasicosUnidadCentro(form);
		});
	}

	setForm() {
		this.unidadCentroForm = new FormGroup({
			id_unidad_centro: new FormControl(this.unidadCentroService.unidadCentro.id_unidad_centro, Validators.required),
			id_ciclo: new FormControl(this.unidadCentroService.unidadCentro.id_ciclo, Validators.required),
			unidad_centro: new FormControl(this.unidadCentroService.unidadCentro.unidad_centro),
			observaciones: new FormControl(this.unidadCentroService.unidadCentro.observaciones)
		});
	}


}
