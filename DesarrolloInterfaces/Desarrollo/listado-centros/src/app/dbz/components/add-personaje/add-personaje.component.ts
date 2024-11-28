import { Component } from '@angular/core';
import { Personaje } from '../../interfaces/personaje.interface';

@Component
(
  {
    selector: 'dbz-add-personaje',
    templateUrl: './add-personaje.component.html',
    styleUrl: './add-personaje.component.css'
  }
)
export class AddPersonajeComponent
{
  public personaje: Personaje =
  {
    nombre: '',
    fuerza: 0
  }

  public addPersonaje(): void
  {
    console.log(this.personaje)
  }
}
