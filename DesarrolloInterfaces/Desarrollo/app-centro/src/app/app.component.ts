import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { CentroComponent } from './centro/centro.component';


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, CentroComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'app-centro';
}
