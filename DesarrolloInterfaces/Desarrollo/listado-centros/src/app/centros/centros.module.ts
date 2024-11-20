import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CentroListComponent } from './components/lista/lista.component';

@NgModule({
  declarations: [CentroListComponent],
  imports: [CommonModule],
  exports: [CentroListComponent]
})
export class CentrosModule {}
