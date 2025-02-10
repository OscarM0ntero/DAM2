import { Routes } from '@angular/router';
import { LayoutComponent } from './components/layout/layout.component';
import { HomeComponent } from './components/home/home.component';
import { BookingComponent } from './components/booking/booking.component';

export const routes: Routes = [
  { path: '', component: LayoutComponent, children: [
      { path: '', component: HomeComponent },
      { path: 'booking', component: BookingComponent }
    ]
  }
];
