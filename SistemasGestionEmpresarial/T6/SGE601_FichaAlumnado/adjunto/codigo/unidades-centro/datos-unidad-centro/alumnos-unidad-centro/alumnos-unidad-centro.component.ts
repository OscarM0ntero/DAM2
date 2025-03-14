import { Component, OnInit, ViewChild } from '@angular/core';
import { AlumnosService } from 'src/app/services/alumnos.service';
import { Alumno } from 'src/app/shared/interfaces/alumno';
import { ActivatedRoute } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { AddAlumnoComponent } from './add-alumno/add-alumno.component';
import { MatTableDataSource } from '@angular/material/table';
import { UnidadesCentroService } from 'src/app/services/unidades-centro.service';
import { EditAlumnoComponent } from './edit-alumno/edit-alumno.component';
import { FormControl } from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { DeleteAlumnoComponent } from './delete-alumno/delete-alumno.component';
import { Overlay } from '@angular/cdk/overlay';


@Component({
	selector: 'app-alumnos-unidad-centro',
	templateUrl: './alumnos-unidad-centro.component.html'
})
export class AlumnosUnidadCentroComponent implements OnInit {

	@ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;
	@ViewChild(MatSort, { static: true }) sort: MatSort;

	alumnos: Alumno[] = [];
	private overlay: Overlay;

	dataSource = new MatTableDataSource<Alumno>();
	displayedColumns: string[];
	idUnidadCentro: number;
	unidadCentro: String;

	nombreFilter = new FormControl();
	apellidosFilter = new FormControl();
	edadFilter = new FormControl();
	linkedinFilter = new FormControl();

	private filterValues = { nombre: '', apellidos: '', edad: '', linkedin: '' };

	constructor(private alumnosService: AlumnosService, private route: ActivatedRoute, public dialog: MatDialog, private unidadCentroService: UnidadesCentroService) { }

	openAddAlumnoDialog(): void {
		const dialogRef = this.dialog.open(AddAlumnoComponent, {
			width: '600px',
			data: { id_unidad_centro: this.idUnidadCentro }
		});

		dialogRef.afterClosed().subscribe(result => {
			if (result) {
				this.getAlumnos();
			}
		});
	}

	ngOnInit(): void {
		this.idUnidadCentro = this.unidadCentroService.unidadCentro?.id_unidad_centro;
		this.unidadCentro = this.unidadCentroService.unidadCentro?.unidad_centro;
		if (this.idUnidadCentro) {
			this.getAlumnos();
		}
	}

	async deleteAlumno(alumno: Alumno) {
		const dialogRef = this.dialog.open(DeleteAlumnoComponent, { data: alumno });
		const RESULT = await dialogRef.afterClosed().toPromise();
		if (RESULT) {
			if (RESULT.ok) {
				this.ngOnInit();
			}
		}
	}

	async addAlumno() {
		const dialogRef = this.dialog.open(AddAlumnoComponent, {
			width: '600px',
			data: { id_unidad_centro: this.idUnidadCentro }
		});
		const RESULT = await dialogRef.afterClosed().toPromise();
		console.log("Resultado: ", RESULT);

		if (RESULT) {
			if (RESULT.ok) {
				console.log("aqui");
				this.ngOnInit();
			}
		}
	}

	async editAlumno(alumno: Alumno) {
		const dialogRef = this.dialog.open(EditAlumnoComponent, {
			width: '600px',
			data: { alumno: alumno }
		});
		const RESULT = await dialogRef.afterClosed().toPromise();
		if (RESULT) {
			if (RESULT.ok) {
				this.ngOnInit();
			}
		}
	}

	getAlumnos() {
		this.alumnosService.getAlumnosPorUnidadCentro(this.idUnidadCentro).subscribe(res => {
			if (res.status) {
				this.displayedColumns = ['nombre', 'apellidos', 'edad', 'linkedin', 'actions'];
				this.dataSource.data = res.data;
				this.dataSource.sort = this.sort;
				this.dataSource.paginator = this.paginator;
				this.dataSource.filterPredicate = this.createFilter();
				this.onChanges();
			} else {
				this.dataSource.data = [];
			}
		});
	}

	createFilter(): (alumno: Alumno, filter: string) => boolean {
		const filterFunction = (alumno: Alumno, filter: string): boolean => {
			const searchTerms = JSON.parse(filter);

			return alumno.nombre.toLowerCase().indexOf(searchTerms.nombre_completo.toLowerCase()) !== -1
				&& alumno.apellidos.toLowerCase().indexOf(searchTerms.nombre_completo.toLowerCase()) !== -1
				&& alumno.fecha_nacimiento.toLowerCase().indexOf(searchTerms.cargo.toLowerCase()) !== -1
				&& alumno.linkedin.toLowerCase().indexOf(searchTerms.familia.toLowerCase()) !== -1;
		};
		return filterFunction;
	}

	onChanges() {
		this.nombreFilter.valueChanges.subscribe(value => {
			this.filterValues.nombre = value;
			this.dataSource.filter = JSON.stringify(this.filterValues);
		});

		this.apellidosFilter.valueChanges
			.subscribe(value => {
				this.filterValues.apellidos = value;
				this.dataSource.filter = JSON.stringify(this.filterValues);
			});

		this.edadFilter.valueChanges
			.subscribe(value => {
				this.filterValues.edad = value;
				this.dataSource.filter = JSON.stringify(this.filterValues);
			});

		this.linkedinFilter.valueChanges
			.subscribe(value => {
				this.filterValues.linkedin = value;
				this.dataSource.filter = JSON.stringify(this.filterValues);
			});
	}

}
