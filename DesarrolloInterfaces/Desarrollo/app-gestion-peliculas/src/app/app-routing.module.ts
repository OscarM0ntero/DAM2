import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Error404PageComponent } from './shared/pages/error404-page/error404-page.component';
import { canActivateGuard, canMatchGuard } from './auth/guards/auth.guard';
import { PublicGuard } from './auth/guards/public.guard';
import { AuthGuardService as AuthGuard } from './guards/auth.guard';

const routes: Routes = [
	{
		path: '',
		redirectTo: 'heroes',
		pathMatch: 'full'
	},
	{
		path: 'auth',
		loadChildren: () => import('./auth/auth.module').then(m => m.AuthModule),
		canActivate: [PublicGuard]
	},
	{
		path: 'heroes',
		loadChildren: () => import('./heroes/heroes.module').then(m => m.HeroesModule),
		canMatch: [canMatchGuard], // Anclamos la funcion del canMatch
		canActivate: [AuthGuard]
	},
	{
		path: '404',
		component: Error404PageComponent
	},
	{
		path: '**',
		redirectTo: '404',
	}
];

@NgModule({
	imports: [RouterModule.forRoot(routes)],
	exports: [RouterModule]
})
export class AppRoutingModule { }
