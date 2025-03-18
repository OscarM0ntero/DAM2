import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environments } from 'src/environments/environments';
import { User } from '../interfaces/user.interface';
import { catchError, map, Observable, of, tap } from 'rxjs';
import { CookieService } from 'ngx-cookie-service';
import { CommonService } from 'src/app/shared/common.service';
import { ApiResponse } from 'src/app/shared/interfaces/api-response';



@Injectable({ providedIn: 'root' })
export class AuthService {

	private baseUrl = environments.baseUrl;
	private user?: User;

	constructor(private http: HttpClient, private cookieService: CookieService, private commonService: CommonService) { }

	get currentUser(): User | undefined {
		if (!this.user) return undefined;

		// Problema: Javascript pasa los objetos por referencia, y podría ser modificado
		// NO USAR
		// return this.user;

		// Opción 1: No permite deep clone, aunque para el caso serviría
		// return { ...this.user };
		return structuredClone(this.user);
	}

	doLogin(data: any) {

		const body = JSON.stringify(data);
		return this.http.post<ApiResponse>(`${this.baseUrl}/login.php`, body);
	}

	doLogout() {
		this.user = undefined;
		localStorage.clear(); // Eliminamos cualquier cosa que haya
	}

	public async isAuthenticated(url: string): Promise<boolean> {

		let rutaSeleccionada: string;
		const promise = new Promise<boolean>((resolve, reject) => {
			rutaSeleccionada = url.substring(1);
			rutaSeleccionada = rutaSeleccionada.split('/')[0];
			this.http.get<ApiResponse>(`${this.baseUrl}/check_usuarios.php?ruta=${rutaSeleccionada}`, { headers: this.commonService.getHeaders() })
				.subscribe((response: ApiResponse) => {
					resolve(response.ok);
				});
		});
		return promise;
	}



}
