import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class GifsService {

  private _historialEtiquetas: string[] = [];

  get historialEtiquetas() {
    return [...this._historialEtiquetas];
  }

  buscarEtiqueta(etiqueta: string): void {
    // Quitamos espacios a la derecha y a la izquierda
    etiqueta = etiqueta.trim();

    // Evitamos busquedas vacías
    if(!etiqueta) {
      return;
    }

    // Comprobamos si el array ya contiene la etiqueta, si es asi la quitamos.
    //Esta comprobacion es case insensitive, osea si el array tiene DBZ y ponemos dbz las considera iguales, y la elimina.
    this._historialEtiquetas.forEach((e)=>{
      if(e.toLowerCase() === etiqueta.toLocaleLowerCase()) {
        this._historialEtiquetas.splice(this._historialEtiquetas.indexOf(e), 1);
      }
    })

    // limita el array a 10, todo lo que lo sobrepase lo elimina del array
    this._historialEtiquetas.splice(9);

    // Añadimos la nueva etiqueta
    this._historialEtiquetas.unshift(etiqueta);

    console.log(this.historialEtiquetas);
  }

  constructor() { }
}
