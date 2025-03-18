import { Component } from '@angular/core';
import { Router } from '@angular/router';

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
		private router: Router
	) { }

	/* 
	onLogout(): void {
		this.authService.logout();
		this.router.navigate(['/auth']);
	}
	
	get user(): User | undefined {
		return this.authService.currentUser;
	}
	*/


}
