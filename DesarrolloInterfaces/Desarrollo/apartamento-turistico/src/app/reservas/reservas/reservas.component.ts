import { Component } from '@angular/core';

@Component({
  selector: 'app-reservas',
  templateUrl: './reservas.component.html',
  styleUrls: ['./reservas.component.css']
})
export class ReservasComponent {
  viewDate: Date = new Date();
  events = [
    {
      start: new Date(),
      end: new Date(),
      title: 'Reserva existente'
    }
  ];
}
