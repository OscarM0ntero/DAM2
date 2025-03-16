import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Pelicula } from '../../interfaces/pelicula.interface';
import { PeliculasService } from '../../services/peliculas.service';
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

	public peliculaForm = new FormGroup({
		id: new FormControl<string>(''),
		superpelicula: new FormControl<string>('', { nonNullable: true }),
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
		private peliculasService: PeliculasService,
		private router: Router,
		private snackbar: MatSnackBar,
		private dialog: MatDialog
	) { }

	ngOnInit(): void {
		if (!this.router.url.includes('edit')) return;

		const route = this.router.url.split('/'); // Tomo la ruta y la divido en secciones
		const id = route[route.length - 1]; // Tomo la ultima seccion, la id del superpeliculae

		this.peliculasService.getPeliculaById(id)
			.subscribe(pelicula => {
				if (!pelicula) {	// Si pasa esto significa que el peliculae no ha cargado correctamente
					this.router.navigate(['/']);	// Volvemos al inicio
					return;
				}

				this.peliculaForm.reset(pelicula);
			});
	}

	get currentPelicula(): Pelicula {
		const pelicula = this.peliculaForm.value as Pelicula;
		return pelicula;
	}

	onSubmit(): void {
		if (this.peliculaForm.invalid) return;

		// Si tenemos un id, queremos actualizar
		if (this.currentPelicula.id) {
			this.peliculasService.updatePelicula(this.currentPelicula)
				.subscribe(pelicula => {
					// TODO: Mostrar snackbar
					this.showSnackbar(`${pelicula.title} updated!`)
				});
			return;
		}

		this.peliculasService.addPelicula(this.currentPelicula)
			.subscribe(pelicula => {
				// TODO: Mostrar snackbar y navegar a /peliculas/edit/pelicula.id
				this.showSnackbar(`${pelicula.title} created!`)
			});
	}

	private showSnackbar(message: string): void {
		this.snackbar.open(message, 'OK', {
			duration: 2500,
		})
	}

	public onDeletePelicula() {
		if (!this.currentPelicula.id) throw Error('Pelicula id is required');

		const dialogRef = this.dialog.open(ConfirmDialogComponent, {
			data: this.peliculaForm.value,
		});

		dialogRef.afterClosed().subscribe(result => {
			if (!result) return;

			this.peliculasService.deletePeliculaById(this.currentPelicula.id!)
				.subscribe({
					next: (success) => {
						if (success) {
							this.showSnackbar(`${this.currentPelicula.title} eliminado correctamente`);
							this.router.navigate(['/peliculas']); // Redirigir al listado de héroes
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
