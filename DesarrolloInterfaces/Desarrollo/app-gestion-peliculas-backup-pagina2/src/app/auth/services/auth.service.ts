import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environments } from 'src/environments/environments';
import { User } from '../interfaces/user.interface';
import { catchError, map, Observable, of, tap } from 'rxjs';
import { CookieService } from 'ngx-cookie-service';


@Injectable({ providedIn: 'root' })
export class AuthService {

	private baseUrl = environments.baseUrl;
	private user?: User;

	constructor(private http: HttpClient, private cookieService: CookieService, private commonService: CommonService)
	{ }

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
		return this.http.post<ApiResponse>(`${URL_API}/login.php`, body);
	}

	login(email: string, password: string): Observable<User> {

		// Backend normal
		// this.http.post('login', {email, password});

		// Nuestro backend
		return this.http.get<User>(`${this.baseUrl}/users/1`)
			.pipe(
				tap(user => this.user = user),
				tap(user => localStorage.setItem('token', 'eHPPv9zY2Kyp3OCr3J+nrLOrmoeOn5R9'))	//eHPPv9zY2Kyp3OCr3J+nrLOrmoeOn5R9
			);
	}

	logout() {
		this.user = undefined;
		localStorage.clear(); // Eliminamos cualquier cosa que haya
	}

	checkAuthentication(): Observable<boolean> {
		if (!localStorage.getItem('token')) return of(false); // no necesitamos operación asíncrona

		const token = localStorage.getItem('token');

		return this.http.get<User>(`${this.baseUrl}/users/1`)
			.pipe(
				tap(user => this.user = user), // tap: efecto secundario para almacenar el usuario
				map(user => !!user),          // map: transformamos la salida, hacemos doble negación, negamos null
				// Básicamente devolvemos true si hay un usuario
				// Es lo mismo que poner map( user => user ? true : false )
				catchError(err => of(false))   // y si el backend devuelve error, es false
			);
	}



}
