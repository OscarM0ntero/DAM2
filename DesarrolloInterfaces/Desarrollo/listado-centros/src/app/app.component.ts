import { Component } from '@angular/core';
import { CentrosModule } from './centros/centros.module';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CentrosModule],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {}
