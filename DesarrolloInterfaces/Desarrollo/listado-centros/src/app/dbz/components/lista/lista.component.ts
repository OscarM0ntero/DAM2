import { Component, Input, Output, EventEmitter } from '@angular/core';
import { Personaje } from '../../interfaces/personaje.interface';

@Component
	(
		{
			selector: 'dbz-lista',
			templateUrl: './lista.component.html',
			styleUrl: './lista.component.css'
		}
	)
export class ListaComponent {

	@Input('miLista')
	public listaPersonajes: Personaje[] =
		[
			{
				nombre: 'Default',
				fuerza: 0
			}
		];

	@Output()
	public personajeEliminado: EventEmitter<Personaje> = new EventEmitter();

	eliminar(personaje: Personaje): void {
		this.personajeEliminado.emit(personaje);
	}
}
