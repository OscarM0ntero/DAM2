import { Component, Input, OnInit } from '@angular/core';
import { Heroe } from '../../interfaces/heroe';
@Component({
  selector: 'app-hero-card',
  templateUrl: './card.component.html',
})
export class HeroCardComponent implements OnInit {
  @Input() heroe!: Heroe ;

  ngOnInit(): void {
    console.log(this.heroe); // Verifica que el dato heroe está llegando correctamente
  }
}
