import { Component } from '@angular/core';

@Component({
  selector: 'app-lista',
  templateUrl: './lista.component.html',
  styleUrls: ['./lista.component.css']
})
export class ListaComponent {
  heroes: string[] = ['Superman', 'Batman', 'Wonder Woman', 'Flash'];

  eliminarHeroe() {
    this.heroes.pop();
  }
}
