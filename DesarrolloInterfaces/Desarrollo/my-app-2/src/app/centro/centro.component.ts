import { Component } from '@angular/core';

interface Centro {
  nombre: string;
  localidad: string;
  familias: string[];
  logoUrl: string;
}

@Component({
  selector: 'app-centro',
  templateUrl: './centro.component.html',
  styleUrls: ['./centro.component.css']
})
export class CentroComponent {
  centros: Centro[] = [
    {
      nombre: 'IES Playamar',
      localidad: 'Torremolinos',
      familias: ['Informática y Comunicaciones', 'Transporte y Mantenimiento'],
      logoUrl: 'assets/logo-playamar.png'
    },
    {
      nombre: 'IES Jacaranda',
      localidad: 'Churriana',
      familias: ['Hostelería y Turismo'],
      logoUrl: 'assets/logo-jacaranda.png'
    }
  ];

  centroActual = this.centros[0];

  cambiarCentro() {
    this.centroActual = this.centroActual === this.centros[0] ? this.centros[1] : this.centros[0];
  }
}
