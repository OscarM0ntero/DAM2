import { Component, Input, OnInit } from '@angular/core';
import { Pelicula } from '../../interfaces/pelicula.interface';

@Component({
  selector: 'peliculas-pelicula-card',
  templateUrl: './card.component.html',
  styles: [
  ]
})
export class CardComponent implements OnInit {
	@Input()
	public pelicula!: Pelicula;
	public noPoster: string = 'assets/no-poster.png';

	ngOnInit(): void {
		// pelicula debe ser inicializado
		if (!this.pelicula) throw new Error('Pelicula property is required.');
	}

	get formattedVoteAverage(): string {
		return this.pelicula.vote_average.toFixed(1);
	}
	
}

