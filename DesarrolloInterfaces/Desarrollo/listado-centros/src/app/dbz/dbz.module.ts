import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { MainPageComponent } from './pages/main-page.component';
import { ListaComponent } from './components/lista/lista.component';
import { AddPersonajeComponent } from './components/add-personaje/add-personaje.component';

@NgModule({
  declarations: [MainPageComponent, ListaComponent, AddPersonajeComponent],
  imports: [CommonModule, FormsModule],
  exports: [MainPageComponent]
})
export class DbzModule { }
