import { Component, OnInit } from '@angular/core';
import { Pelicula } from '../../interfaces/pelicula.interface';
import { PeliculasService } from '../../services/peliculas.service';

@Component({
	selector: 'app-fav-list-page',
	templateUrl: './fav-list-page.component.html',
	styleUrls: ['./fav-list-page.component.css']
})
export class FavListPageComponent implements OnInit {

	peliculas: Pelicula[] = [];

	constructor(private peliculasService: PeliculasService,) { }

	ngOnInit(): void {
		this.peliculasService.getPeliculas()
			.subscribe({
				next: (peliculas) => {
					this.peliculas = peliculas;
				}
			});
	}
}
