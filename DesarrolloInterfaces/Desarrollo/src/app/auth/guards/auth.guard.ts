import { inject } from '@angular/core';
import { AuthService } from './../services/auth.service';
import { ActivatedRouteSnapshot, CanActivateFn, Router, RouterStateSnapshot } from "@angular/router";
import { Observable, tap } from "rxjs";

const checkAuthStatus = (route: ActivatedRouteSnapshot): Observable<boolean> => {
  const authService: AuthService = inject(AuthService);
  const router = inject(Router);

  if (route.url[0].path === 'login') {
    return new Observable<boolean>(observer => {
      observer.next(true);
      observer.complete();
    });
  }


  return authService.checkAuth().pipe(
    tap(isAuthenticated => {
      if (!isAuthenticated) {
        router.navigate(['/login']);
      }
    })
  );
}

export const canActivateGuard: CanActivateFn = (
  route: ActivatedRouteSnapshot,
  state: RouterStateSnapshot
): Observable<boolean> => {
  console.log("canActivateGuard", route, state);
  return checkAuthStatus(route);
}
