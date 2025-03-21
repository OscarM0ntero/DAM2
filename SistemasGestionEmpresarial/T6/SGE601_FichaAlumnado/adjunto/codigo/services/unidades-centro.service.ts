import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { URL_API } from 'src/environments/environment';
import { CommonService } from '../shared/common.service';
import { UnidadCentro } from '../shared/interfaces/unidad-centro';
import { ApiResponse } from '../shared/interfaces/api-response';

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
		return this.http.get<ApiResponse>(`${URL_API}/${ENDPOINT}.php`, { headers: this.commonService.headers });
	}

	getUnidades() {
		return this.http.get<ApiResponse>(`${URL_API}/${ENDPOINT}.php`, { headers: this.commonService.headers });
	}

	getAllUnidadesCentro() {
		return this.http.get<ApiResponse>(`${URL_API}/${ENDPOINT}.php`, { headers: this.commonService.headers });
	}

	addUnidadCentro(unidadCentro: UnidadCentro) {
		const body = JSON.stringify(unidadCentro);
		return this.http.post<ApiResponse>(`${URL_API}/${ENDPOINT}.php`, body, { headers: this.commonService.headers });
	}

	editUnidadCentro(unidadCentro: UnidadCentro) {
		const body = JSON.stringify(unidadCentro);
		return this.http.put<ApiResponse>(`${URL_API}/${ENDPOINT}.php`, body, { headers: this.commonService.headers });
	}

	deleteUnidadCentro(idUnidadCentro: string | number) {
		return this.http.delete<ApiResponse>(`${URL_API}/${ENDPOINT}.php?id=${idUnidadCentro}`, { headers: this.commonService.headers });
	}

}
