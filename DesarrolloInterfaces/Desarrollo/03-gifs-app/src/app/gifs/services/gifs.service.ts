import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Gif, SearchResponse } from '../interfaces/gifs.interfaces';

@Injectable({
  providedIn: 'root'
})
export class GifsService {
  public listadoGifs : Gif[] = [];

  private _historialEtiquetas: string[] = [];
  private apiKey: string = 'cwGdcRIf84VsUQP2bLsTEm0LgUhjyaBy';
  private serviceUrl: string = 'https://api.giphy.com/v1/gifs'

  constructor( private http: HttpClient ) {

  }

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

    const params = new HttpParams()
    .set('api_key', this.apiKey)
    .set('limit', 12)
    .set('q', etiqueta)

    this.http.get<SearchResponse>(`${ this.serviceUrl }/search`, { params }).subscribe( resp => {
      this.listadoGifs = resp.data;
      console.log({ gifs: this.listadoGifs });
    })
  }

}
