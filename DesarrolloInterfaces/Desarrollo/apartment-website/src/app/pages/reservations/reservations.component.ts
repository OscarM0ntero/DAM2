import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-reservations',
  templateUrl: './reservations.component.html',
  styleUrls: ['./reservations.component.scss']
})
export class ReservationsComponent {

  onSubmit(form: NgForm) {
    if (form.valid) {
      const { checkIn, checkOut, guests } = form.value;
      alert(`Reserva confirmada para ${guests} huésped(es) del ${checkIn} al ${checkOut}.`);
      form.reset();
    }
  }
}
