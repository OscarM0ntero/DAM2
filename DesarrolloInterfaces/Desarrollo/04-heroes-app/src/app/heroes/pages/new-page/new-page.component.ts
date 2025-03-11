import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Hero, Publisher } from '../../interfaces/hero.interface';
import { HeroesService } from '../../services/heroes.service';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmDialogComponent } from '../../components/confirm-dialog/confirm-dialog.component';

@Component({
	selector: 'app-new-page',
	templateUrl: './new-page.component.html',
	styles: [
	]
})
export class NewPageComponent {

	public heroForm = new FormGroup({
		id: new FormControl<string>(''),
		superhero: new FormControl<string>('', { nonNullable: true }),
		publisher: new FormControl<Publisher>(Publisher.DCComics),
		alter_ego: new FormControl(''),
		first_appearance: new FormControl(''),
		characters: new FormControl(''),
		alt_img: new FormControl('')
	});

	public publishers = [
		{ id: 'DC Comics', desc: 'DC - Comics' },
		{ id: 'Marvel Comics', desc: 'Marvel - Comics' }
	];

	constructor(
		private heroesService: HeroesService,
		private router: Router,
		private snackbar: MatSnackBar,
		private dialog: MatDialog
	) { }

	ngOnInit(): void {
		if (!this.router.url.includes('edit')) return;

		const route = this.router.url.split('/'); // Tomo la ruta y la divido en secciones
		const id = route[route.length - 1]; // Tomo la ultima seccion, la id del superheroe

		this.heroesService.getHeroById(id)
			.subscribe(hero => {
				if (!hero) {	// Si pasa esto significa que el heroe no ha cargado correctamente
					this.router.navigate(['/']);	// Volvemos al inicio
					return;
				}

				this.heroForm.reset(hero);
			});
	}

	get currentHero(): Hero {
		const hero = this.heroForm.value as Hero;
		return hero;
	}

	onSubmit(): void {
		if (this.heroForm.invalid) return;

		// Si tenemos un id, queremos actualizar
		if (this.currentHero.id) {
			this.heroesService.updateHero(this.currentHero)
				.subscribe(hero => {
					// TODO: Mostrar snackbar
					this.showSnackbar(`${hero.superhero} updated!`)
				});
			return;
		}

		this.heroesService.addHero(this.currentHero)
			.subscribe(hero => {
				// TODO: Mostrar snackbar y navegar a /heroes/edit/hero.id
				this.showSnackbar(`${hero.superhero} created!`)
			});
	}

	private showSnackbar(message: string): void {
		this.snackbar.open(message, 'OK', {
			duration: 2500,
		})
	}

	public onDeleteHero() {
		if (!this.currentHero.id) throw Error('Hero id is required');

		const dialogRef = this.dialog.open(ConfirmDialogComponent, {
			data: this.heroForm.value,
		});

		dialogRef.afterClosed().subscribe(result => {
			if (!result) return;

			this.heroesService.deleteHeroById(this.currentHero.id!)
				.subscribe({
					next: (success) => {
						if (success) {
							this.showSnackbar(`${this.currentHero.superhero} eliminado correctamente`);
							this.router.navigate(['/heroes']); // Redirigir al listado de héroes
						} else {
							this.showSnackbar('Error al eliminar el héroe');
						}
					},
					error: () => {
						this.showSnackbar('Error al eliminar el héroe');
					}
				});
		});
	}



}
