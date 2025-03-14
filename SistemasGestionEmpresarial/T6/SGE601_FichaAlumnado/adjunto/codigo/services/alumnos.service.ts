import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { URL_API } from 'src/environments/environment';
import { CommonService } from '../shared/common.service';
import { Alumno } from '../shared/interfaces/alumno';
import { ApiResponse } from '../shared/interfaces/api-response';
import { tap } from 'rxjs/operators';

const ENDPOINT = 'alumnos';

@Injectable({
	providedIn: 'root'
})
export class AlumnosService {

	alumnos: Alumno[];
	alumno: Alumno;

	constructor(private http: HttpClient, private commonService: CommonService) { }

	setAlumno(alumno: Alumno) {
		this.alumno = alumno;
	}

	getAlumnosPorUnidadCentro(idUnidadCentro: number) {
		return this.http.get<ApiResponse>(
			`${URL_API}/${ENDPOINT}.php?id_unidad_centro=${idUnidadCentro}`,
			{ headers: this.commonService.headers }
		);
	}

	addAlumno(alumno: Alumno) {
		const body = JSON.stringify(alumno);
		return this.http.post<ApiResponse>(
			`${URL_API}/${ENDPOINT}.php`,
			body,
			{ headers: this.commonService.headers }
		);
	}

	updateAlumno(alumno: any) {
		return this.http.put<ApiResponse>(`${URL_API}/alumnos.php`, alumno)
			.pipe(
				tap(res => console.log('Respuesta bruta del backend:', res))
			);
	}

	editAlumno(alumno: Alumno) {
		const body = JSON.stringify(alumno);
		return this.http.put<ApiResponse>(
			`${URL_API}/${ENDPOINT}.php`,
			body,
			{ headers: this.commonService.headers }
		);
	}

	deleteAlumno(id: string) {
		return this.http.delete<ApiResponse>(
			`${URL_API}/${ENDPOINT}.php?id=${id}`,
			{ headers: this.commonService.headers }
		);
	}
}
