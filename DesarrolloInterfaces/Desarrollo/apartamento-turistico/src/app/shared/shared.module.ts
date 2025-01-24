import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavbarComponent } from './navbar/navbar.component';  // Componente Navbar
import { FooterComponent } from './footer/footer.component';  // Componente Footer

@NgModule({
  declarations: [
    NavbarComponent,   // Declara NavbarComponent
    FooterComponent    // Declara FooterComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
    NavbarComponent,   // Exporta NavbarComponent para que otros módulos puedan usarlo
    FooterComponent    // Exporta FooterComponent para que otros módulos puedan usarlo
  ]
})
export class SharedModule { }
