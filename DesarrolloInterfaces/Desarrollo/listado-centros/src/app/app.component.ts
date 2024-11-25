
import { Component } from '@angular/core';
import { HeroesModule } from './heroes/heroes.module';
import { CentrosModule } from './centros/centros.module';
import { DbzModule } from './dbz/dbz.module';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [HeroesModule, CentrosModule, DbzModule],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {}
