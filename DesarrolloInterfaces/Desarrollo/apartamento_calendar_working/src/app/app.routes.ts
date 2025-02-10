import { Routes } from '@angular/router';
import { LayoutComponent } from './components/layout/layout.component';
import { HomeComponent } from './components/home/home.component';
import { BookingComponent } from './components/booking/booking.component';
import { Booking2Component } from './components/booking2/booking2.component';

export const routes: Routes = [
  { path: '', component: LayoutComponent, children: [
      { path: '', component: HomeComponent },
      { path: 'booking', component: Booking2Component },
	  { path: 'booking2', component: Booking2Component }
    ]
  }
];
