import { Component, OnInit, Input } from '@angular/core';
import { Personaje } from '../../interfaces/personaje.interface';

@Component
(
  {
    selector: 'dbz-lista',
    templateUrl: './lista.component.html',
    styleUrl: './lista.component.css'
  }
)
export class ListaComponent
{

  @Input('miLista')
  public listaPersonajes: Personaje[] =
  [
    {
      nombre: 'Default',
      fuerza: 0
    }
  ]
}
