import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/auth/services/auth.service';

@Component({
  selector: 'app-layout-page',
  templateUrl: './layout-page.component.html',
  styles: [
  ]
})
export class LayoutPageComponent {

  constructor(private authService: AuthService, private router: Router) {}

  public sidebarItems = [
    {label: 'Listado', icon: 'label',url: './list'},
    {label: 'Añadir', icon: 'add', url: './new-hero'},
    {label: 'Buscar', icon: 'search', url: './search'}

  ]

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/auth/login']);
  }

}
