import { Component, OnInit } from '@angular/core';
import { PeliculasService } from '../../services/peliculas.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Pelicula } from '../../interfaces/pelicula.interface';
import { delay, switchMap } from 'rxjs';

@Component({
	selector: 'app-pelicula-page',
	templateUrl: './pelicula-page.component.html',
	styleUrls: ['./pelicula-page.component.css']
})
export class PeliculaPageComponent implements OnInit {

	public pelicula?: Pelicula;
	hover: boolean = false;
	public backgroundImg?: string;

	constructor(
		private peliculasService: PeliculasService,
		private activatedRoute: ActivatedRoute,
		private router: Router,
	) { }

	ngOnInit(): void {
		this.pelicula = undefined;

		this.activatedRoute.params
			.pipe(
				// Math.floor(Math.random() * (1000 - 0 + 1))
				switchMap(({ id }) => {
					return this.peliculasService.getPeliculaById(id); })
			)
			.subscribe(pelicula => {

				if (!pelicula) return this.router.navigate(['/peliculas']);

				this.pelicula = pelicula;
				this.peliculasService.getFondoById(this.pelicula.id).subscribe(fondo => {
					this.backgroundImg = fondo;
				});

				return;
			});
	}

	goBack(): void {
		this.router.navigate(['/peliculas/list']);
	}

	getFlag(lang: string): string {
		console.log(lang);
		const languageToCountryMap: { [key: string]: string } = {
			'en': 'GB', // Inglés - Reino Unido
			'es': 'ES', // Español - España
			'it': 'IT', // Italiano - Italia
			'de': 'DE', // Alemán - Alemania
			'fr': 'FR', // Francés - Francia
			'ja': 'JP', // Japonés - Japón
			'ko': 'KR', // Coreano - Corea del Sur
			'zh': 'CN', // Chino - China
			'pt': 'PT', // Portugués - Portugal
			'ru': 'RU', // Ruso - Rusia
			'ar': 'SA', // Árabe - Arabia Saudita
			'nl': 'NL', // Neerlandés - Países Bajos
			'tr': 'TR', // Turco - Turquía
			'hi': 'IN', // Hindi - India
			'bn': 'BD', // Bengalí - Bangladés
			'pa': 'IN', // Panyabí - India
			'ur': 'PK', // Urdú - Pakistán
			'vi': 'VN', // Vietnamita - Vietnam
			'th': 'TH', // Tailandés - Tailandia
			'fa': 'IR', // Persa - Irán
			'pl': 'PL', // Polaco - Polonia
			'uk': 'UA', // Ucraniano - Ucrania
			'cs': 'CZ', // Checo - República Checa
			'ro': 'RO', // Rumano - Rumanía
			'hu': 'HU', // Húngaro - Hungría
			'el': 'GR', // Griego - Grecia
			'sv': 'SE', // Sueco - Suecia
			'da': 'DK', // Danés - Dinamarca
			'fi': 'FI', // Finés - Finlandia
			'no': 'NO', // Noruego - Noruega
			'he': 'IL', // Hebreo - Israel
			'id': 'ID', // Indonesio - Indonesia
			'ms': 'MY', // Malayo - Malasia
			'tl': 'PH', // Tagalo - Filipinas
			'mn': 'MN', // Mongol - Mongolia
			'ka': 'GE', // Georgiano - Georgia
			'et': 'EE', // Estonio - Estonia
			'lv': 'LV', // Letón - Letonia
			'lt': 'LT', // Lituano - Lituania
			'sk': 'SK', // Eslovaco - Eslovaquia
			'sl': 'SI', // Esloveno - Eslovenia
			'hr': 'HR', // Croata - Croacia
			'sr': 'RS', // Serbio - Serbia
			'bg': 'BG', // Búlgaro - Bulgaria
			'mk': 'MK', // Macedonio - Macedonia
			'sq': 'AL', // Albanés - Albania
			'hy': 'AM', // Armenio - Armenia
			'az': 'AZ', // Azerí - Azerbaiyán
			'kk': 'KZ', // Kazajo - Kazajistán
			'uz': 'UZ', // Uzbeko - Uzbekistán
			'tk': 'TM', // Turcomano - Turkmenistán
			'my': 'MM', // Birmano - Birmania
			'km': 'KH', // Jemer - Camboya
			'lo': 'LA', // Lao - Laos
			'si': 'LK', // Cingalés - Sri Lanka
			'ml': 'IN', // Malabar - India
			'te': 'IN', // Telugu - India
			'kn': 'IN', // Canarés - India
			'ta': 'IN', // Tamil - India
			'hk': 'HK', // Hong Kong - Región Administrativa Especial de China
			'yue': 'HK',   // Cantonés - Código ISO 639-3 para cantonés
			'cn': 'CN'

		};

		const countryCode = languageToCountryMap[lang];

		if (!countryCode) return '🏳️'; // Si no existe, muestra una bandera blanca

		// Convertir el código ISO a puntos Unicode de bandera
		return countryCode
			.split('')
			.map(char => String.fromCodePoint(0x1F1E6 + char.charCodeAt(0) - 65))
			.join('');
	}


	formatAverage(number: number): string {
		return number.toFixed(1);
	}


}
