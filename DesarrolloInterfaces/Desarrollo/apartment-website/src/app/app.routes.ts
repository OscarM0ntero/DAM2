import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { ApartmentDetailsComponent } from './pages/apartment-details/apartment-details.component';
import { GalleryComponent } from './pages/gallery/gallery.component';
import { ReservationsComponent } from './pages/reservations/reservations.component';
import { LocationComponent } from './pages/location/location.component';
import { ReviewsComponent } from './pages/reviews/reviews.component';
import { ContactComponent } from './pages/contact/contact.component';
import { FaqComponent } from './pages/faq/faq.component';
import { BlogComponent } from './pages/blog/blog.component';

// Array de rutas
export const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'apartment-details', component: ApartmentDetailsComponent },
  { path: 'gallery', component: GalleryComponent },
  { path: 'reservations', component: ReservationsComponent },
  { path: 'location', component: LocationComponent },
  { path: 'reviews', component: ReviewsComponent },
  { path: 'contact', component: ContactComponent },
  { path: 'faq', component: FaqComponent },
  { path: 'blog', component: BlogComponent },
  { path: '**', redirectTo: 'home' }
];
