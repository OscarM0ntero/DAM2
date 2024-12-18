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
        id: '',
				nombre: 'Default',
				fuerza: 0
			}
		];

	@Output()
	public eliminarPersonaje: EventEmitter<string> = new EventEmitter();

	eliminar(id: string): void {
		this.eliminarPersonaje.emit(id);
	}
}
