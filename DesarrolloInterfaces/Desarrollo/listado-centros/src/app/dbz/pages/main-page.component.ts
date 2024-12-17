import { Component, OnInit } from '@angular/core';
import { Personaje } from '../interfaces/personaje.interface';

@Component({
	selector: 'app-dbz-main-page',
	templateUrl: './main-page.component.html'
})

export class MainPageComponent {
	public personajes: Personaje[] =
		[
			{
				nombre: 'Goku',
				fuerza: 1000
			},
			{
				nombre: 'Krilin',
				fuerza: 500
			},
			{
				nombre: 'Vegetta',
				fuerza: 700
			},
			{
				nombre: 'Bulma',
				fuerza: 300
			}
		];

	public onNewPersonaje(personaje: Personaje): void {
		this.personajes.push(personaje);
	}
	
	public onEliminarPersonaje(personaje: Personaje): void {
		this.personajes = this.personajes.filter(p => p !== personaje);
		console.log('Personaje eliminado:', personaje);
	}
}
