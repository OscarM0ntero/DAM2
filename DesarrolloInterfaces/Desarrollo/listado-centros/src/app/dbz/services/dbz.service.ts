import { Injectable } from '@angular/core';
import { Personaje } from '../interfaces/personaje.interface';
import { v4 as uuid } from 'uuid';

@Injectable({providedIn: 'root'})
export class DbzService {
  constructor() { }

  public personajes: Personaje[] =
      [
        {
          id: uuid(),
          nombre: 'Goku',
          fuerza: 1000
        },
        {
          id: uuid(),
          nombre: 'Krilin',
          fuerza: 500
        },
        {
          id: uuid(),
          nombre: 'Vegetta',
          fuerza: 700
        },
        {
          id: uuid(),
          nombre: 'Bulma',
          fuerza: 300
        }
      ];

    public onNewPersonaje(personaje: Omit<Personaje, 'id'>): void {
      const newPersonaje = { ...personaje, id: uuid() };
      this.personajes.push(newPersonaje);
    }

    //public onDeletePersonaje(personaje: Personaje): void {
    //	this.personajes = this.personajes.filter(p => p !== personaje);
    //	console.log('Personaje eliminado:', personaje);
    //}

    public deletePersonajeById(id: string) {
      this.personajes = this.personajes.filter ( personaje => personaje.id !== id );
    }

}
