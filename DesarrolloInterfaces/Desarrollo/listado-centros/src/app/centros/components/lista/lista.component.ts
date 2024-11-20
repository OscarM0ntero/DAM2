import { Component } from '@angular/core';

@Component({
  selector: 'app-centro-list',
  templateUrl: './lista.component.html',
  styleUrls: ['./lista.component.css']
})
export class CentroListComponent {
  centros = [
    {
      nombre: 'IES Playamar',
      localidad: 'Torremolinos',
      ciclos: ['Informática y Comunicaciones', 'Transporte y Mantenimiento'],
      logo: 'logo-playamar.png'
    },
    {
      nombre: 'IES Jacaranda',
      localidad: 'Churriana',
      ciclos: ['Hostelería y Turismo'],
      logo: 'logo-jacaranda.jpeg'
    },
    {
      nombre: 'IES La Rosaleda',
      localidad: 'Málaga',
      ciclos: ['Sanidad', 'Actividades Físicas'],
      logo: 'logo-rosaleda.jpg'
    },
    {
      nombre: 'IES Los Manantiales',
      localidad: 'Torremolinos',
      ciclos: ['Administración de Sistemas Informáticos en Red', 'Estética Integral y Bienestar', 'Gestión de Alojamientos Turísticos', 'Estilismo y Dirección de Peluquería'],
      logo: 'logo-manantiales.jpg'
    }
  ];

  pila: any[] = [];
  indiceActual = 0;

  get centroActual() {
    return this.centros[this.indiceActual];
  }

  anterior() {
    if (this.indiceActual > 0) {
      this.indiceActual--;
    }
  }

  posterior() {
    if (this.indiceActual < this.centros.length - 1) {
      this.indiceActual++;
    }
  }

  eliminar() {
    if (this.centros.length > 0) {
      const eliminado = this.centros.splice(this.indiceActual, 1)[0];
      this.pila.push(eliminado);

      if (this.indiceActual >= this.centros.length) {
        this.indiceActual = this.centros.length - 1;
      }
    }
  }

  insertar() {
    if (this.pila.length > 0) {
      const centro = this.pila.pop();
      this.centros.splice(this.indiceActual, 0, centro);
    }
  }

  deshabilitarAnterior() {
    return this.indiceActual === 0;
  }

  deshabilitarPosterior() {
    return this.indiceActual === this.centros.length - 1;
  }
}
