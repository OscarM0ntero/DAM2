import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HeroesRoutingModule } from './heroes-routing.module';
import { HeroPageComponent } from './pages/hero-page/hero-page.component';
import { ListPageComponent } from './pages/list-page/list-page.component';
import { NewPageComponent } from './pages/new-page/new-page.component';
import { SearchPageComponent } from './pages/search-page/search-page.component';

import { MaterialModule } from '../material/material.module';
import { HeroCardComponent } from './components/card/card.component';
import { SharedModule } from "../shared/shared.module";
import { HeroImagePipe } from './pipes/hero-image.pipe';
import { ReactiveFormsModule } from '@angular/forms';
import { LayoutPageComponent } from './pages/layout-page/layout-page.component';
@NgModule({
  declarations: [
    HeroPageComponent,
    LayoutPageComponent,
    ListPageComponent,
    NewPageComponent,
    SearchPageComponent,
    HeroCardComponent,
    HeroImagePipe

  ],
  imports: [
    CommonModule,
    HeroesRoutingModule,
    MaterialModule,
    SharedModule,
    ReactiveFormsModule
]
})
export class HeroesModule { }
