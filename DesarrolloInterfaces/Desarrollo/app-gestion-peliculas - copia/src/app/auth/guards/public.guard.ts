import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { Observable } from 'rxjs';
import { map, catchError } from 'rxjs/operators';

@Injectable({
	providedIn: 'root'
})
export class PublicGuard implements CanActivate {

	constructor(private authService: AuthService, private router: Router) { }

	canActivate(
		route: ActivatedRouteSnapshot,
		state: RouterStateSnapshot): Observable<boolean> {

		return this.authService.checkAuthentication().pipe(
			map(isAuthenticated => {
				if (isAuthenticated) {
					this.router.navigate(['/heroes']);
					return false;
				}
				return true;
			}),
			catchError(() => {
				this.router.navigate(['/auth/login']);
				return [false];
			})
		);
	}
}
