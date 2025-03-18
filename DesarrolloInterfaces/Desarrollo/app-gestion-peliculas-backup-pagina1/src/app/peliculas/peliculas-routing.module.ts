import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LayoutPageComponent } from './pages/layout-page/layout-page.component';
import { SearchPageComponent } from './pages/search-page/search-page.component';
import { ListPageComponent } from './pages/list-page/list-page.component';
import { PeliculaPageComponent } from './pages/pelicula-page/pelicula-page.component';

const routes: Routes = [
  {
    // localhost:4200/auth/
    path: '',
    component: LayoutPageComponent,
    children: [
      { path: 'search/:query', component: SearchPageComponent},
      { path: 'list', component: ListPageComponent},
      { path: ':id', component: PeliculaPageComponent},
      { path: '**', redirectTo: 'search/'}
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PeliculasRoutingModule { }
