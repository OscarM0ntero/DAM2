import { Component, OnInit } from '@angular/core';
import { Pelicula } from '../../interfaces/pelicula.interface';
import { PeliculasService } from '../../services/peliculas.service';

@Component({
	selector: 'app-list-page',
	templateUrl: './list-page.component.html',
	
	styles: []
})
export class ListPageComponent implements OnInit {

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
