import { Component, ViewChild, ElementRef} from '@angular/core';
import { GifsService } from './../../services/gifs.service';

@Component({
  selector: 'gifs-search-box',
  templateUrl: './search-box.component.html',
  styleUrls: ['./search-box.component.css']
})
export class SearchBoxComponent {
  @ViewChild('txtInputEtiqueta')
  public inputEtiqueta!: ElementRef<HTMLInputElement>;

  constructor( private gifsService: GifsService ) { }

  buscarEtiqueta() {
    const nuevaEtiqueta = this.inputEtiqueta.nativeElement.value;
    this.gifsService.buscarEtiqueta(nuevaEtiqueta);
    this.inputEtiqueta.nativeElement.value = "";
  }
}
