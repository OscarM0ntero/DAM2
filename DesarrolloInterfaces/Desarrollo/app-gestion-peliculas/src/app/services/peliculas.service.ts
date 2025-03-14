import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonService } from '../shared/common.service';
import { Pelicula } from '../shared/interfaces/pelicula';
import { ApiResponse } from '../shared/interfaces/api-response';
import { environments } from 'src/environments/environments';

const ENDPOINT = 'unidad_centro';

@Injectable({
	providedIn: 'root'
})
export class UnidadesCentroService {

	unidadCentro: UnidadCentro;

	constructor(private http: HttpClient, private commonService: CommonService) { }

	setUnidadCentro(unidadCentro: UnidadCentro) {
		this.unidadCentro = unidadCentro;
	}

	setDatosBasicosUnidadCentro(formUnidadCentro: any) {
		this.unidadCentro.id_unidad_centro = formUnidadCentro.id_unidad_centro;
		this.unidadCentro.id_ciclo = formUnidadCentro.id_ciclo;
		this.unidadCentro.unidad_centro = formUnidadCentro.unidad_centro;
		this.unidadCentro.observaciones = formUnidadCentro.observaciones;
	}

	get() {
		return this.http.get<ApiResponse>(`${environments.baseUrl}/${ENDPOINT}.php`, { headers: this.commonService.headers });
	}

	getUnidades() {
		return this.http.get<ApiResponse>(`${environments.baseUrl}/${ENDPOINT}.php`, { headers: this.commonService.headers });
	}

	getAllUnidadesCentro() {
		return this.http.get<ApiResponse>(`${environments.baseUrl}/${ENDPOINT}.php`, { headers: this.commonService.headers });
	}

	addUnidadCentro(unidadCentro: UnidadCentro) {
		const body = JSON.stringify(unidadCentro);
		return this.http.post<ApiResponse>(`${environments.baseUrl}/${ENDPOINT}.php`, body, { headers: this.commonService.headers });
	}

	editUnidadCentro(unidadCentro: UnidadCentro) {
		const body = JSON.stringify(unidadCentro);
		return this.http.put<ApiResponse>(`${environments.baseUrl}/${ENDPOINT}.php`, body, { headers: this.commonService.headers });
	}

	deleteUnidadCentro(idUnidadCentro: string | number) {
		return this.http.delete<ApiResponse>(`${environments.baseUrl}/${ENDPOINT}.php?id=${idUnidadCentro}`, { headers: this.commonService.headers });
	}

}
