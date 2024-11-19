import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CentroListComponent } from './components/pila/pila.component';

@NgModule({
  declarations: [CentroListComponent],
  imports: [CommonModule],
  exports: [CentroListComponent]
})
export class CentrosModule {}
