import { User } from './../interfaces/user.interface';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environments';
import { catchError, map, Observable, of, tap } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private baseUrl = environment.baseUrl;
  private user?: User;

  constructor(private http: HttpClient) {
   }

   get usuario(): User | undefined {
      if (!this.user) return undefined;

      return structuredClone(this.user);
    }
    login(usuario: string, password: string): Observable<any> {
      console.log('Login:',
        `Usuario: ${usuario}, Password: ${password}`
      );
      return this.http.get<any[]>(`${this.baseUrl}/users`).pipe(
        map(users => {
          // Buscamos el usuario con el email que coincide y validamos la contraseña
          const foundUser = users.find(user => user.usuario === usuario && user.password === password);
          if (foundUser) {
            this.user = foundUser;  // Guarda al usuario si la autenticación es exitosa
            localStorage.setItem('token', foundUser.id);
            return foundUser;
          } else {
            throw new Error('Credenciales incorrectas');
          }
        })
      );
    }

    logout(): void {
      this.user = undefined;  // Limpia el usuario
      console.log('Antes del logout', localStorage.getItem('token'));

      localStorage.removeItem('token');  // Elimina el "token" del localStorage

      console.log('Después del logout', localStorage.getItem('token'));
    }

    checkAuth(): Observable<boolean> {
      // Verifica si existe un token en localStorage
      const token = localStorage.getItem('token');

      if (!token) {
        return of(false);  // Si no hay token, retorna false inmediatamente
      }

      // Si el token existe, verifica si el usuario es válido a través de una solicitud HTTP
      return this.http.get<User>(`${this.baseUrl}/users/1`).pipe(
        tap(user => this.user = user),  // Almacena el usuario si es válido
        map(user => !!user),  // Si el usuario existe, retorna true, de lo contrario false
        catchError(err => {
          // Si ocurre un error (por ejemplo, error en la red o el usuario no existe), retorna false
          console.error('Error en la validación del usuario:', err);
          return of(false);
        })
      );
    }

}
