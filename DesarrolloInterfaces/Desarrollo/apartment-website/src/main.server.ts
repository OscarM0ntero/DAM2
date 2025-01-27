import { enableProdMode } from '@angular/core';
import { AppServerModule } from './app/app.server.module';

if (environment.production) {
  enableProdMode();
}

export { AppServerModule } from './app/app.server.module';
