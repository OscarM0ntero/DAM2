import { HeroesService } from './../../services/heroes.service';
import { Component } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Heroe } from '../../interfaces/heroe';
import { MatAutocompleteSelectedEvent } from '@angular/material/autocomplete';
import { HeroCardComponent } from '../../components/card/card.component';
@Component({
  selector: 'app-search-page',
  templateUrl: './search-page.component.html',
  styles: [
  ]
})
export class SearchPageComponent {



  public searchInput = new FormControl('', Validators.required);
  public heroes: Heroe[] = [];
  public selectHero: Heroe | undefined;

  constructor(private heroesService: HeroesService) { }

  public searchHero(): void {
    const value: string = this.searchInput.value || '';

    this.heroesService.getSuggestions(value).subscribe(
      heroes => this.heroes = heroes
    );
  }

  onSelectedOption($event: MatAutocompleteSelectedEvent) {
    console.log({ $event });
    }
}
