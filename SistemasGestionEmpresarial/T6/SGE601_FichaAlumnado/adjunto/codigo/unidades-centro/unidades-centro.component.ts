import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';
import { MatDialog } from '@angular/material/dialog';
import { Overlay } from '@angular/cdk/overlay';
import { FormControl } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';

import { UnidadCentro } from '../shared/interfaces/unidad-centro';
import { AddUnidadCentroComponent } from './add-unidad-centro/add-unidad-centro.component';
import { EditUnidadCentroComponent } from './edit-unidad-centro/edit-unidad-centro.component';
import { DeleteUnidadCentroComponent } from './delete-unidad-centro/delete-unidad-centro.component';

import { UnidadesCentroService } from '../services/unidades-centro.service';
import { DatosUnidadCentroComponent } from './datos-unidad-centro/datos-unidad-centro.component';
import { CiclosService } from '../services/ciclos.service';
import { Ciclo } from '../shared/interfaces/ciclo';

@Component({
	selector: 'app-unidades-centro',
	templateUrl: './unidades-centro.component.html',
	styleUrls: ['./unidades-centro.component.scss']
})
export class UnidadesCentroComponent implements OnInit {
	@ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;
	@ViewChild(MatSort, { static: true }) sort: MatSort;

	dataSource: MatTableDataSource<UnidadCentro> = new MatTableDataSource();

	unidadCentroFilter = new FormControl();
	idCicloFilter = new FormControl();
	observacionesFilter = new FormControl();
	ciclos: Ciclo[];



	displayedColumns: string[] = ['unidad_centro', 'id_ciclo', 'observaciones', 'actions'];

	constructor(
		public dialog: MatDialog,
		private unidadesCentroService: UnidadesCentroService,
		private overlay: Overlay,
		private snackBar: MatSnackBar,
		private servicioCiclos: CiclosService
	) { }

	ngOnInit(): void {
		this.getUnidadesCentros();
		this.getCiclos();
	}

	async getUnidadesCentros() {
		try {
			const RESPONSE = await this.unidadesCentroService.getAllUnidadesCentro().toPromise();

			if (RESPONSE.status && RESPONSE.data?.data) {
				this.dataSource = new MatTableDataSource(RESPONSE.data.data);
				this.dataSource.sort = this.sort;
				this.dataSource.paginator = this.paginator;
			} else {
				this.snackBar.open('Error: Datos inválidos recibidos', 'Cerrar', { duration: 5000 });
			}

		} catch (error) {
			this.snackBar.open('Error al obtener unidades centro', 'Cerrar', { duration: 5000 });
		}
	}

	async addUnidadCentro() {
		const dialogRef = this.dialog.open(AddUnidadCentroComponent, {
			scrollStrategy: this.overlay.scrollStrategies.noop(),
			disableClose: true
		});

		const RESULT = await dialogRef.afterClosed().toPromise();
		if (RESULT?.ok) {
			this.getUnidadesCentros();
		}
	}

	async editUnidadCentro(unidadCentro: UnidadCentro) {
		const dialogRef = this.dialog.open(EditUnidadCentroComponent, {
			data: unidadCentro,
			scrollStrategy: this.overlay.scrollStrategies.noop()
		});

		const RESULT = await dialogRef.afterClosed().toPromise();
		if (RESULT?.ok) {
			this.getUnidadesCentros();
		}
	}

	async deleteUnidadCentro(unidadCentro: UnidadCentro) {
		const dialogRef = this.dialog.open(DeleteUnidadCentroComponent, {
			data: unidadCentro,
			scrollStrategy: this.overlay.scrollStrategies.noop()
		});

		const RESULT = await dialogRef.afterClosed().toPromise();
		if (RESULT?.ok) {
			this.getUnidadesCentros();
		}
	}

	async datosUnidadCentro(unidadCentro: UnidadCentro) {
		const UNIDAD_CENTRO = unidadCentro;


		if (UNIDAD_CENTRO) {
			const dialogRef = this.dialog.open(DatosUnidadCentroComponent, {
				width: '70em',
				maxWidth: '70em',
				scrollStrategy: this.overlay.scrollStrategies.noop(),
				disableClose: true,
				data: {
					unidadCentro: UNIDAD_CENTRO,
					ciclos: this.ciclos,
				}
			});

			const RESULT = await dialogRef.afterClosed().toPromise();
		}
	}



	async getCiclos() {
		const RESPONSE = await this.servicioCiclos.getAllCiclos().toPromise();
		if (RESPONSE.ok) {
			console.log("OK");
			this.ciclos = RESPONSE.data as Ciclo[];
		}
	}

	getCicloById(id_ciclo: number): string | undefined {
		const ciclo = this.ciclos.find(c => c.id_ciclo === id_ciclo);
		return ciclo ? ciclo.ciclo : undefined;
	}
}

