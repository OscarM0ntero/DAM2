import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/auth/interfaces/user.interface';
import { AuthService } from 'src/app/auth/services/auth.service';

@Component({
	selector: 'app-layout-page',
	templateUrl: './layout-page.component.html',
	styleUrls: ['./layout-page.component.css']

})
export class LayoutPageComponent {
	
	currentYear = new Date().getFullYear();

	public sidebarItems = [
		{ label: 'Buscar', icon: 'search', url: './search/' },
		{ label: 'Favoritos', icon: 'label', url: './fav-list' },
		{ label: 'Añadir', icon: 'add', url: './new-hero' },
		
	];

	constructor(
		private router: Router,
		private authService: AuthService
	) { }

	
	onLogout(): void {
		this.authService.doLogout();
		this.router.navigate(['/auth']);
	}
	
	get user(): string | null {
		return localStorage.getItem('nombre_publico');
	}
	


}
