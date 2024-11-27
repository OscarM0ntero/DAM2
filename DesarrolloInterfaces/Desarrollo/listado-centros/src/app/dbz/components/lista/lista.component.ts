import { Component } from '@angular/core';

@Component({
  selector: 'dbz-lista',
  templateUrl: './lista.component.html',
  styleUrl: './lista.component.css'
})
export class ListaComponent {
  personajes = [
    {
      nombre: 'Goku',
      fuerza: '10000'
    },
    {
      nombre: 'Yamsha',
      fuerza: '500'
    }
  ];
}
