import { Component, OnInit, Injectable } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Heroe, Publisher } from '../../interfaces/heroe';
import { HeroesService } from '../../services/heroes.service';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmDialogComponent } from '../../components/confirm-dialog/confirm-dialog.component';
@Component({
  selector: 'app-new-page',
  templateUrl: './new-page.component.html',
  styles: [],
})
export class NewPageComponent implements OnInit {
  public heroForm = new FormGroup({
    id: new FormControl<string>(''),
    superhero: new FormControl<string>('', { nonNullable: true }),
    publisher: new FormControl<Publisher>(Publisher.DCComics),
    alter_ego: new FormControl(''),
    first_appearance: new FormControl(''),
    characters: new FormControl(''),
    alt_img: new FormControl(''),
  });

  public publishers = [
    {
      id: 'DC Comics',
      desc: 'DC - Comics',
    },
    {
      id: 'Marvel Comics',
      desc: 'Marvel - Comics',
    },
  ];

  constructor(
    private heroesService: HeroesService,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private snackBar: MatSnackBar,
    private dialog: MatDialog
  ) {}

  get currentHero(): Heroe {
    return this.heroForm.value as Heroe;

  }

  get heroImage(): string {
    return this.heroForm.get('alt_img')?.value?.trim() || 'assets/no-image.png';
  }

  private showSnackbar(message: string): void {
    this.snackBar.open(message, 'OK', { duration: 2500 });
  }

  onSubmit(): void {
    if (this.heroForm.invalid) return;

    let heroToSave = this.heroForm.value as Heroe;

    if (!heroToSave.id || heroToSave.id.trim() === '') {
      heroToSave = {
        ...heroToSave,
        id: this.generateId(),
      };
    }

    if (!heroToSave.alt_img || heroToSave.alt_img.trim() === '') {
      heroToSave = {
        ...heroToSave,
        alt_img: 'assets/no-image.png',
      };
    }

    if (!this.currentHero.id) {
      this.heroesService.addHeroe(heroToSave).subscribe((heroe) => {
        console.log('Hero created:', heroe);
        this.showSnackbar(`Héroe ${heroe.superhero} creado.`);
      });
    } else {
      this.heroesService.updateHeroe(heroToSave).subscribe((heroe) => {
        console.log('Hero updated:', heroe);
        this.showSnackbar(`Héroe ${heroe.superhero} actualizado.`);
      });
    }
  }

  private generateId(): string {
    return Math.random().toString(36).substr(2, 9);
  }

  ngOnInit(): void {
    if (!this.router.url.includes('edit')) return;

    this.activatedRoute.params.subscribe((params: Params) => {
      const id = params['id'];

      this.heroesService.getHeroeById(id).subscribe((heroe) => {
        if (!heroe) {
          this.showSnackbar('Héroe no encontrado');
          this.router.navigate(['/heroes']);
          return;
        }

        const heroFormValues = {
          ...heroe,
          publisher: heroe.publisher as Publisher,
        };

        this.heroForm.reset(heroFormValues);
      });
    });
  }

  public onDeleteHero(): void {
    if (!this.currentHero.id) {
      throw new Error('El ID del héroe es requerido para eliminarlo.');
    }

    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      data: this.heroForm.value,
    });

    dialogRef.afterClosed().subscribe((result: boolean) => {
      if (!result) return;

      this.heroesService.deleteHero(this.currentHero.id).subscribe(
        (success: boolean) => {
          if (success) {
            this.showSnackbar(`Héroe eliminado correctamente.`);
            this.router.navigate(['/heroes']);
          } else {
            this.showSnackbar(`Error al eliminar el héroe. Inténtalo de nuevo.`);
          }
        },
        () => {
          this.showSnackbar(`Error al eliminar el héroe. Inténtalo de nuevo.`);
        }
      );
    });
  }
}
