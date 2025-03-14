import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UnidadCentro } from 'src/app/shared/interfaces/unidad-centro';
import { UnidadesCentroService } from 'src/app/services/unidades-centro.service';
import { CLOSE, ENTIDAD_UNIDAD_CENTRO } from 'src/app/shared/messages';

@Component({
  selector: 'app-delete-unidad-centro',
  templateUrl: './delete-unidad-centro.component.html',
  styleUrls: ['./delete-unidad-centro.component.scss']
})
export class DeleteUnidadCentroComponent implements OnInit {

  ENTIDAD: String;

  constructor(
    public dialogRef: MatDialogRef<DeleteUnidadCentroComponent>,
    @Inject(MAT_DIALOG_DATA) public unidadCentro: UnidadCentro,
    public servicioUnidadesCentro: UnidadesCentroService,
    public snackBar: MatSnackBar,
  ) 
  {   }

  ngOnInit(): void {   
    this.ENTIDAD = ENTIDAD_UNIDAD_CENTRO;
  }

  onNoClick(): void {
    this.dialogRef.close({ ok: false });
  }

  async confirmDelete() {
    const RESPONSE = await this.servicioUnidadesCentro.deleteUnidadCentro(this.unidadCentro.id_unidad_centro).toPromise();
    if (RESPONSE.ok) {
      this.snackBar.open(RESPONSE.message, CLOSE, { duration: 5000 });
      this.dialogRef.close({ ok: RESPONSE.ok, data: RESPONSE.data });
    } else { this.snackBar.open(RESPONSE.message, CLOSE, { duration: 5000 }); }
  }

}
