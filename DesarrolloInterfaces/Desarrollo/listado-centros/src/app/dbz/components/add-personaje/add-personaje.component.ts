import { Component, EventEmitter, Output } from '@angular/core';
import { Personaje } from '../../interfaces/personaje.interface';


@Component
	(
		{
			selector: 'dbz-add-personaje',
			templateUrl: './add-personaje.component.html',
			styleUrl: './add-personaje.component.css'
		}
	)
export class AddPersonajeComponent {
	@Output()
	public onNewPersonaje: EventEmitter<Omit<Personaje, 'id'>> = new EventEmitter();

	public personaje: Omit<Personaje, 'id'> =
		{
			nombre: '',
			fuerza: 0
		}

	public addPersonaje(): void {

		console.log(this.personaje)

		if (this.personaje.nombre.length === 0) return;

		this.onNewPersonaje.emit(this.personaje);

		// Inicializamos valores
		this.personaje = {
			nombre: '',
			fuerza: 0
		}

	}
}
