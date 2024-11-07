import { Component } from '@angular/core';
import { ContadorComponent } from './contador/contador.component';

@Component({
  selector: 'app-root',
  standalone: true,
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
  imports: [ ContadorComponent ],
})
export class AppComponent
{
  public title: string = 'Mi Aplicacion';
  public contador: number = 0;

  // Si se le pasa el valor 0, resetea el contador
  cambiarPor(x: number): void
  {
    if (x==0)
      x = this.contador * -1
    this.contador += x;
  }
}
