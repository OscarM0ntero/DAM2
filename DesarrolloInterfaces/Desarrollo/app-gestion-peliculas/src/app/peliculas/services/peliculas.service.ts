import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { catchError, forkJoin, map, Observable, of } from 'rxjs';
import { Pelicula, Search } from '../interfaces/pelicula.interface';
import { environments } from 'src/environments/environments';

@Injectable({ providedIn: 'root' })
export class PeliculasService {

	private baseUrl: string = environments.baseUrl;
	private apiKey: string = environments.apiKey;


	constructor(private http: HttpClient) { }

	getPeliculas(): Observable<Pelicula[]> {
		return this.http.get<Pelicula[]>(`${this.baseUrl}/peliculas`);
	}

	getPeliculaById(id: string): Observable<Pelicula | undefined> {
		return this.http.get<Pelicula>(`${this.baseUrl}/movie/${id}?api_key=${this.apiKey}`)
			.pipe(
				catchError(() => of(undefined))
			);
	}

	getSearch(query: string): Observable<Pelicula[]> {
		const requests = [];

		for (let i = 1; i <= 3; i++) {
			requests.push(
				this.http.get<Search>(`${this.baseUrl}/search/movie?api_key=${this.apiKey}&query=${query}&page=${i}`)
			);
		}

		return forkJoin(requests).pipe(
			map(results => {
				// Combinar todos los resultados en un solo array
				return results
					.flatMap(result => result.results)
					.sort((a, b) => b.popularity - a.popularity); // Ordenar de mayor a menor
			})
		);
	}



	addPelicula(pelicula: Pelicula): Observable<Pelicula> {
		return this.http.post<Pelicula>(`${this.baseUrl}/peliculas`, pelicula)
	}

	updatePelicula(pelicula: Pelicula): Observable<Pelicula> {
		if (!pelicula.id) throw Error('Pelicula id is required');

		return this.http.patch<Pelicula>(`${this.baseUrl}/peliculas/${pelicula.id}`, pelicula);
	}

	deletePeliculaById(id: string): Observable<boolean> {
		return this.http.delete(`${this.baseUrl}/peliculas/${id}`)
			.pipe(
				map(response => true),
				catchError(error => of(false))
			)
	}


}

// https://api.themoviedb.org/3/movie/242582?api_key=3b5faf6c57b79d39e76351663a5e0cbe
// https://api.themoviedb.org/3/search/movie?api_key=3b5faf6c57b79d39e76351663a5e0cbe&query=nightcrawler