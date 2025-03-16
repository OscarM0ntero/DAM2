import { Component, OnInit } from '@angular/core';
import { PeliculasService } from '../../services/peliculas.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Pelicula } from '../../interfaces/pelicula.interface';
import { delay, switchMap } from 'rxjs';

@Component({
	selector: 'app-pelicula-page',
	templateUrl: './pelicula-page.component.html',
	styles: [
	]
})
export class PeliculaPageComponent implements OnInit {

	public pelicula?: Pelicula;

	constructor(
		private peliculasService: PeliculasService,
		private activatedRoute: ActivatedRoute,
		private router: Router,

	) { }

	ngOnInit(): void {
		this.activatedRoute.params
			.pipe(
				delay(1000),
				switchMap(({ id }) => this.peliculasService.getPeliculaById(id))
			)
			.subscribe(pelicula => {

				if (!pelicula) return this.router.navigate(['/peliculas']);

				this.pelicula = pelicula;
				console.log(pelicula);

				return;
			});
	}

	goBack(): void {
		this.router.navigate(['/peliculas/list']);
	}

}
