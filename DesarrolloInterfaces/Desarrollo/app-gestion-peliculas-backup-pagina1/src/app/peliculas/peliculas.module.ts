import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PeliculasRoutingModule } from './peliculas-routing.module';
import { PeliculaPageComponent } from './pages/pelicula-page/pelicula-page.component';
import { LayoutPageComponent } from './pages/layout-page/layout-page.component';
import { ListPageComponent } from './pages/list-page/list-page.component';
import { SearchPageComponent } from './pages/search-page/search-page.component';
import { MaterialModule } from '../material/material.module';
import { CardComponent } from './components/card/card.component';
//import { HeroImagePipe } from './pipes/hero-image.pipe';
import { ReactiveFormsModule } from '@angular/forms';
import { NoPosterPipe } from './pipes/no-poster.pipe';


@NgModule({
  declarations: [
    PeliculaPageComponent,
    LayoutPageComponent,
    ListPageComponent,
    SearchPageComponent,
    CardComponent,
    NoPosterPipe,
  ],
  imports: [
    CommonModule,
    PeliculasRoutingModule,
	MaterialModule,
	ReactiveFormsModule,
	
  ]
})
export class PeliculasModule { }
