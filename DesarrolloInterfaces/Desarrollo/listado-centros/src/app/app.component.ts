
import { Component } from '@angular/core';
import { HeroesModule } from './heroes/heroes.module';
import { CentrosModule } from './centros/centros.module';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [HeroesModule, CentrosModule],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {}
