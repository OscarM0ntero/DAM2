import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { routes } from './app.routes';

// Importa los componentes que creaste en /pages
import { HomeComponent } from './pages/home/home.component';
import { ApartmentDetailsComponent } from './pages/apartment-details/apartment-details.component';
import { GalleryComponent } from './pages/gallery/gallery.component';
import { ReservationsComponent } from './pages/reservations/reservations.component';
import { LocationComponent } from './pages/location/location.component';
import { ReviewsComponent } from './pages/reviews/reviews.component';
import { ContactComponent } from './pages/contact/contact.component';
import { FaqComponent } from './pages/faq/faq.component';
import { BlogComponent } from './pages/blog/blog.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ApartmentDetailsComponent,
    GalleryComponent,
    ReservationsComponent,
    LocationComponent,
    ReviewsComponent,
    ContactComponent,
    FaqComponent,
    BlogComponent
  ],
  imports: [
    BrowserModule,            // Render en navegador
    RouterModule.forRoot(routes), // <-- Aquí inyectamos el array de rutas
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {}
