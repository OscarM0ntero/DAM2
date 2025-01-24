import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home/home.component';
import { ReservasComponent } from './reservas/reservas/reservas.component';
import { ContactoComponent } from './contacto/contacto/contacto.component';

const routes: Routes = [
  { path: '', component: HomeComponent }, // Página principal
  { path: 'reservas', component: ReservasComponent }, // Página de reservas
  { path: 'contacto', component: ContactoComponent }, // Página de contacto
  { path: '**', redirectTo: '' } // Redirección si la ruta no existe
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
