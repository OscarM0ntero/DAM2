import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.scss']
})
export class ContactComponent {

  onSubmit(form: NgForm) {
    if (form.valid) {
      const { name, email, message } = form.value;
      alert(`Mensaje enviado:\nNombre: ${name}\nEmail: ${email}\nMensaje: ${message}`);
      form.reset();
    }
  }
}
