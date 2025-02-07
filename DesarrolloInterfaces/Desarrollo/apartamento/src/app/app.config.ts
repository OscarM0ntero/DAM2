import { NgImageSliderModule } from 'ng-image-slider';
import { ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';

export const appConfig: ApplicationConfig = {
	providers: [provideZoneChangeDetection({ eventCoalescing: true }), importProvidersFrom(NgImageSliderModule),
    provideRouter(routes)]
};
