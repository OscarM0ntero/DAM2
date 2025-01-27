import 'zone.js/dist/zone-node';
import { AppServerModule } from './src/app/app.server.module';
import { ngExpressEngine } from '@nguniversal/express-engine';
import express from 'express';
import { join } from 'path';

export function app(): express.Express {
  const server = express();
  const distFolder = join(process.cwd(), 'dist/apartment-website/browser');
  const indexHtml = 'index';

  // Servir archivos estáticos
  server.use(express.static(distFolder));

  // Configurar motor de vistas con Universal
  server.engine('html', ngExpressEngine({
    bootstrap: AppServerModule,
  }));

  server.set('view engine', 'html');
  server.set('views', distFolder);

  // Para cualquier ruta, renderizamos con Angular
  server.get('*', (req, res) => {
    res.render(indexHtml, { req });
  });

  return server;
}

function run(): void {
  const port = process.env['PORT'] || 4000;
  const serverApp = app();
  serverApp.listen(port, () => {
    console.log(`Node Express server running on http://localhost:${port}`);
  });
}

if (require.main === module) {
  run();
}

export * from './src/main.server';
