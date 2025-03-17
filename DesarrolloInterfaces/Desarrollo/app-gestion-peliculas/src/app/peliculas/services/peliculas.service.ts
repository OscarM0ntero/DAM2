import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { catchError, forkJoin, map, Observable, of } from 'rxjs';
import { Pelicula, Search } from '../interfaces/pelicula.interface';
import { environments } from 'src/environments/environments';

@Injectable({ providedIn: 'root' })
export class PeliculasService {

	private baseUrl: string = environments.baseUrl;
	private apiKey: string = environments.apiKey;

	private imageUrl: string = environments.imageUrl;
	private backgroundUrl: string = environments.backgroundUrl;


	constructor(private http: HttpClient) { }

	getPeliculas(): Observable<Pelicula[]> {
		return this.http.get<Pelicula[]>(`${this.baseUrl}/peliculas`);
	}

	getPeliculaById(id: string): Observable<Pelicula | undefined> {
		return this.http.get<Pelicula>(`${this.baseUrl}/movie/${id}?api_key=${this.apiKey}`)
			.pipe(
				map(pelicula => {
					if (pelicula) {
						pelicula.poster_path = pelicula.poster_path
							? `${this.imageUrl}${pelicula.poster_path}`
							: 'assets/no-poster.png';

						pelicula.backdrop_path = pelicula.backdrop_path
							? `${this.imageUrl}${pelicula.backdrop_path}`
							: 'assets/white.png';
					}
					return pelicula;
				}),
				catchError(() => of(undefined))
			);
	}

	getSearch(query: string): Observable<Pelicula[]> {
		const requests = [];

		// Cada página devuelve 20 películas, para mostrar 60 reunimos 3 páginas
		for (let i = 1; i <= 3; i++) {
			requests.push(
				this.http.get<Search>(`${this.baseUrl}/search/movie?api_key=${this.apiKey}&query=${query}&page=${i}`)
			);
		}

		return forkJoin(requests).pipe(
			map(results => {
				return results
					.flatMap(result => result.results)
					.map(pelicula => {
						// Modificar los datos directamente en el servicio
						pelicula.poster_path = pelicula.poster_path
							? `${this.imageUrl}${pelicula.poster_path}`
							: 'assets/no-poster.png';

						pelicula.backdrop_path = pelicula.backdrop_path
							? `${this.imageUrl}${pelicula.backdrop_path}`
							: 'assets/white.png';

						return pelicula;
					})
					.sort((a, b) => b.popularity - a.popularity); // Ordenar por popularidad
			})
		);
	}

	getFondoById(id: string): Observable<string | undefined> {
		const url = `${this.baseUrl}/movie/${id}/images?api_key=${this.apiKey}`;

		return this.http.get<any>(url).pipe(
			map(response => {
				if (!response.backdrops || response.backdrops.length === 0) return undefined;

				// Ordenar primero por vote_count (descendente) y luego por vote_average (descendente)
				const bestBackdrop = response.backdrops
					.sort((a: any, b: any) => {
						if (b.vote_count !== a.vote_count) {
							return b.vote_count - a.vote_count; // Ordenar por vote_count (mayor primero)
						}
						return b.vote_average - a.vote_average; // Si hay empate, ordenar por vote_average (mayor primero)
					})[0];

				// Devolver la URL completa de la imagen
				return bestBackdrop ? `${this.backgroundUrl}${bestBackdrop.file_path}` : undefined;
			}),
			catchError(() => of(undefined)) // Si hay algún error, devolver undefined
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