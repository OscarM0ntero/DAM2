import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';  // Importa AppRoutingModule para las rutas
import { SharedModule } from './shared/shared.module';    // Importa SharedModule donde están los componentes Navbar y Footer
import { AppComponent } from './app.component';            // Importa AppComponent, el componente raíz

@NgModule({
  declarations: [
    AppComponent,  // Declara AppComponent
    // otros componentes si los tienes...
  ],
  imports: [
    BrowserModule,      // Importa BrowserModule para que funcione en el navegador
    AppRoutingModule,   // Importa AppRoutingModule para las rutas
    SharedModule,       // Importa SharedModule donde están Navbar y Footer
  ],
  providers: [],
  bootstrap: [AppComponent]  // Define que AppComponent es el componente raíz
})
export class AppModule { }
