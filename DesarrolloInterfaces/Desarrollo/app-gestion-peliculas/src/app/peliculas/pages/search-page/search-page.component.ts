import { Component } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Pelicula } from '../../interfaces/pelicula.interface';
import { PeliculasService } from '../../services/peliculas.service';
import { MatAutocompleteSelectedEvent } from '@angular/material/autocomplete';

@Component({
	selector: 'app-search-page',
	templateUrl: './search-page.component.html',
	styles: []
})

export class SearchPageComponent {
	public searchInput = new FormControl('');
	public peliculas: Pelicula[] = [];
	public selectedPelicula?: Pelicula;

	constructor(private peliculasService: PeliculasService) { }

	public searchPelicula() {
		const value: string = this.searchInput.value || '';
		this.peliculasService.getSearch(value).subscribe(peliculas => {
			this.peliculas = peliculas;
	
			// Modificar los datos
			this.peliculas.forEach((pelicula) => {
				console.log(pelicula.poster_path);
				if(pelicula.poster_path != null)
					pelicula.poster_path = "https://image.tmdb.org/t/p/w600_and_h900_bestv2" + pelicula.poster_path;
				else
					pelicula.poster_path = "assets/no-poster.png"
				if(pelicula.backdrop_path != null)
					pelicula.backdrop_path = "https://image.tmdb.org/t/p/w600_and_h900_bestv2" + pelicula.backdrop_path;
				else
					pelicula.backdrop_path = "assets/white.png"
			});
	
			// Reasignar para que Angular detecte el cambio
			this.peliculas = [...this.peliculas];
		});
		
		console.log(this.peliculas);
	}

	public onSelectedOption(event: MatAutocompleteSelectedEvent) {
		if (!event.option.value) {
			this.selectedPelicula = undefined;
			return;
		}
		const pelicula: Pelicula = event.option.value;
		this.searchInput.setValue(pelicula.title);
		this.selectedPelicula = pelicula;

	}
}
