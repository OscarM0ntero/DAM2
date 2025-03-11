import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { catchError, map, Observable, of } from 'rxjs';
import { Heroe } from '../interfaces/heroe';
import { environment } from 'src/environments/environments';

@Injectable({
  providedIn: 'root',
})
export class HeroesService {

  private baseUrl: string = environment.baseUrl;

  constructor(private http: HttpClient) {}

  /**
   * Obtiene todos los héroes.
   */
  getHeroes(): Observable<Heroe[]> {
    return this.http.get<Heroe[]>(`${this.baseUrl}/heroes`).pipe(
      catchError((error) => {
        console.error('Error al obtener los héroes', error);
        return of([]); // Retorna un arreglo vacío en caso de error
      })
    );
  }

  /**
   * Obtiene un héroe por ID.
   * @param id ID del héroe.
   */
  getHeroeById(id: string): Observable<Heroe | undefined> {
    return this.http.get<Heroe>(`${this.baseUrl}/heroes/${id}`).pipe(
      catchError((error) => {
        console.error('Error al obtener el héroe', error);
        return of(undefined); // Retorna undefined si no se encuentra el héroe
      })
    );
  }

  /**
   * Obtiene sugerencias de héroes basado en el término de búsqueda.
   * @param term Término de búsqueda.
   */
  getSuggestions(term: string): Observable<Heroe[]> {
    return this.http.get<Heroe[]>(`${this.baseUrl}/heroes?q=${term}&_limit=6`).pipe(
      catchError((error) => {
        console.error('Error al obtener sugerencias', error);
        return of([]); // Retorna un arreglo vacío en caso de error
      })
    );
  }

  /**
   * Agrega un nuevo héroe.
   * @param hero Héroe a agregar.
   */
  addHeroe(hero: Heroe): Observable<Heroe> {
    return this.http.post<Heroe>(`${this.baseUrl}/heroes`, hero).pipe(
      catchError((error) => {
        console.error('Error al agregar héroe', error);
        return of({} as Heroe); // Retorna un héroe vacío en caso de error
      })
    );
  }

  /**
   * Actualiza un héroe existente.
   * @param hero Héroe a actualizar.
   */
  updateHeroe(hero: Heroe): Observable<Heroe> {
    return this.http.put<Heroe>(`${this.baseUrl}/heroes/${hero.id}`, hero).pipe(
      catchError((error) => {
        console.error('Error al actualizar héroe', error);
        return of({} as Heroe); // Retorna un héroe vacío en caso de error
      })
    );
  }

  /**
   * Elimina un héroe por ID.
   * @param id ID del héroe a eliminar.
   */
  deleteHero(id: string): Observable<boolean> {
    return this.http.delete<Heroe>(`${this.baseUrl}/heroes/${id}`).pipe(
      map(response => true),
      catchError(error => of(false))
    );
  }
}
