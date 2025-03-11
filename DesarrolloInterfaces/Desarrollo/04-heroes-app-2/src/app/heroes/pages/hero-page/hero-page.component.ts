import { Component, OnInit } from '@angular/core';
import { Heroe } from '../../interfaces/heroe';
import { HeroesService } from '../../services/heroes.service';
import { ActivatedRoute, Router } from '@angular/router';
import { delay, switchMap } from 'rxjs';
import { HeroCardComponent } from '../../components/card/card.component';
@Component({
  selector: 'app-hero-page',
  templateUrl: './hero-page.component.html',
  styles: [
  ]
})
export class HeroPageComponent implements OnInit {

  public heroe!: Heroe;

  constructor(
    private heroesService: HeroesService,
    private activatedRoute: ActivatedRoute,
    private router: Router
  )
  {

  }
  ngOnInit(): void {
    console.log(this.activatedRoute.params);
    this.activatedRoute.params
    .pipe(

      switchMap( ({ id }) => this.heroesService.getHeroeById(id) )
    ).subscribe( heroe => {
      if (!heroe) return this.router.navigate(['/heroes/list']);

      this.heroe = heroe;
      console.log(heroe);

      return;
    });
  }
}
