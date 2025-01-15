import { Component } from '@angular/core';
import { GifsService } from 'src/app/gifs/services/gifs.service';

@Component({
  selector: 'shared-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent {

  etiquetas: string[] = [];

  constructor(private gifsService: GifsService) { }

  get historialEtiquetas(): string[] {
    return this.gifsService.historialEtiquetas;
  }

  buscarEtiqueta(etiqueta: string) : void {
    this.gifsService.buscarEtiqueta(etiqueta);
  }
}
